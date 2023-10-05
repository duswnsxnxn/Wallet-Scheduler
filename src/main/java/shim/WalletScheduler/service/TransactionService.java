package shim.WalletScheduler.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import shim.WalletScheduler.entity.WalletQueues;
import shim.WalletScheduler.entity.Wallets;
import shim.WalletScheduler.repository.WalletQueuesRepository;
import shim.WalletScheduler.repository.WalletsRepository;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final WalletQueuesRepository queuesRepository;
    private final WalletsRepository walletsRepository;
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void processQueue(WalletQueues queue) {
        Wallets wallets = walletsRepository.findByIdForUpdate(queue.getWalletId())
                .orElseGet(() -> createAndSaveNewWallet(queue));

        if (wallets.getBalance() != null) {
            wallets.changeBalance(queue);
        }

        this.deleteQueue(queue);
    }

    private Wallets createAndSaveNewWallet(WalletQueues queue) {
        Wallets new_wallet = Wallets.createWallets(queue);
        return walletsRepository.save(new_wallet);
    }

    public void deleteQueue(WalletQueues queue) {
        queuesRepository.delete(queue);
    }

}
