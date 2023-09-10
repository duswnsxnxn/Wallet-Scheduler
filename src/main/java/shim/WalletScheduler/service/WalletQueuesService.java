package shim.WalletScheduler.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shim.WalletScheduler.entity.WalletQueues;
import shim.WalletScheduler.repository.WalletQueuesRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
public class WalletQueuesService {

    private final WalletQueuesRepository queuesRepository;

    @Transactional(readOnly = true)
    public List<WalletQueues> getWalletQueues() {

            return queuesRepository.findTop100By();
    }
    @Transactional
    @Scheduled()
    public void calc() {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        CompletableFuture.runAsync(() -> {
            List<WalletQueues> walletQueues = getWalletQueues();
        });

    }

}
