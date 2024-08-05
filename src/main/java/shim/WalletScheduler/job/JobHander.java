package shim.WalletScheduler.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import shim.WalletScheduler.entity.WalletQueues;
import shim.WalletScheduler.service.WalletQueuesService;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class JobHander {

    private final WalletQueuesService queueService;
    private long startTime = System.currentTimeMillis();
    private boolean isRunning = true;

    public void run() {

        if (!isRunning) {
            return;
        }

        if (startTime == 0) {
            startTime = System.currentTimeMillis();
            log.info("Scheduler started at: {}", startTime);
        }

        List<WalletQueues> walletQueues = queueService.getWalletQueues();

        if (walletQueues.isEmpty()) {
            long elapsedTime = System.currentTimeMillis() - startTime;
            log.info("No more data in WalletQueues. Stopping the job. Elapsed time: {} ms", elapsedTime);
            stopScheduler();
            return;
        }

        queueService.calc(walletQueues);
    }

    private void stopScheduler() {
        isRunning = false;
    }
}
