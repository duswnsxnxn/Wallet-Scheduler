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

    @Transactional
    public void processQueue(WalletQueues queue) {
        Optional<Wallets> optionalWallets = walletsRepository.findById(queue.getWalletId());
        if (optionalWallets.isPresent()) {
            Wallets wallets = optionalWallets.get();
            wallets.changeBalance(wallets);
        } else {
            Wallets new_wallet = Wallets.createWallets(queue);
            this.saveWallet(new_wallet);
        }
        this.deleteQueue(queue);
    }

    public void saveWallet(Wallets wallets) {
        walletsRepository.save(wallets);
    }

    public void deleteQueue(WalletQueues queue) {
        queuesRepository.delete(queue);
    }

}
