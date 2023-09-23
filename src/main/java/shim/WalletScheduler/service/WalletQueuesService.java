package shim.WalletScheduler.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shim.WalletScheduler.entity.WalletQueues;
import shim.WalletScheduler.entity.Wallets;
import shim.WalletScheduler.repository.WalletQueuesRepository;
import shim.WalletScheduler.repository.WalletsRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
@EnableScheduling
@Transactional
@Slf4j
public class WalletQueuesService {

    private final WalletQueuesRepository queuesRepository;
    private final WalletsRepository walletsRepository;
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    public List<WalletQueues> getWalletQueues() {
            return queuesRepository.findTop100By();
    }
    @Scheduled(fixedRate = 100)
    public void calc() {
        List<WalletQueues> queues = queuesRepository.findTop100By();

        if (!getWalletQueues().isEmpty()) {
            for (WalletQueues queue : queues) {
                CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
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
                        }, executorService);
                try {
                    future.get();
                } catch (InterruptedException | ExecutionException e) {
                    log.error(
                            "error: {}",
                            e.toString());
                }
            }
        }
        executorService.shutdown();
    }
}
