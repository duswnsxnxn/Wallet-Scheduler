package shim.WalletScheduler.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import shim.WalletScheduler.entity.WalletQueues;
import shim.WalletScheduler.entity.Wallets;
import shim.WalletScheduler.repository.WalletQueuesRepository;
import shim.WalletScheduler.repository.WalletsRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final WalletQueuesRepository queuesRepository;
    private final WalletsRepository walletsRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void processQueue(WalletQueues queue) {
        Optional<Wallets> optionalWallets = walletsRepository.findById(queue.getWalletId());
        if (optionalWallets.isPresent()) {
            Wallets wallets = optionalWallets.get();
            wallets.setBalances(wallets.getBalances().add(queue.getBalances()));
            walletsRepository.save(wallets);
        } else {
            Wallets new_wallet = new Wallets();
            new_wallet.setBalances(queue.getBalances());
            new_wallet.setWallet_id(queue.getWalletId());
            walletsRepository.save(new_wallet);
        }
        queuesRepository.delete(queue);
    }

}