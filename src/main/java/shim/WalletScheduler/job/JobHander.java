package shim.WalletScheduler.job;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import shim.WalletScheduler.entity.WalletQueues;
import shim.WalletScheduler.service.WalletQueuesService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JobHander {

    private final WalletQueuesService queueService;

    public void run() {
        List<WalletQueues> walletQueues = queueService.getWalletQueues();
        queueService.calc(walletQueues);
    }
}
