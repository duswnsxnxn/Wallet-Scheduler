package shim.WalletScheduler.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import shim.WalletScheduler.job.JobHander;

@Component
@RequiredArgsConstructor
public class WalletScheduler {

    private final JobHander jobHander;

    @Scheduled(fixedRate = 100)
    public void schedule() {
//        jobHander.
    }
}
