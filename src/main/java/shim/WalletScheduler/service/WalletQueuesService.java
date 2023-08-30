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
import java.util.concurrent.ForkJoinPool;

@Service
@RequiredArgsConstructor
public class WalletQueuesService {

    private final WalletQueuesRepository queuesRepository;

    @Transactional
    @Scheduled()
    public void getWalletQueues() {


        List<WalletQueues> top100 = queuesRepository.findTop100By();


    }
}
