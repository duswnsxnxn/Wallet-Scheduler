package shim.WalletScheduler.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import shim.WalletScheduler.entity.WalletQueues;
import shim.WalletScheduler.repository.WalletQueuesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

@Service
@RequiredArgsConstructor
@Slf4j
public class WalletQueuesService {

    private final WalletQueuesRepository queuesRepository;
    private final TransactionService transactionService;
    private final ExecutorService executorService;

    public List<WalletQueues> getWalletQueues() {
        return queuesRepository.getWalletQueuesBy(PageRequest.of(0 , 100));
    }

    public void calc(List<WalletQueues> queues) {

        if (queues.isEmpty()) {
            return;
        }

        List<CompletableFuture<Void>> futures = new ArrayList<>();

        for (WalletQueues queue : queues) {
            CompletableFuture<Void> future =
                    CompletableFuture.runAsync(() -> transactionService.processQueue(queue), executorService);
            futures.add(future);
            }

        try {
            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("error: {}", e.toString());
        }
        }
}
