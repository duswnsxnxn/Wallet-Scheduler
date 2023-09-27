package shim.WalletScheduler.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class WalletConfig {

    @Bean(destroyMethod = "shutdown")
    public ExecutorService executorService() {
        return Executors.newFixedThreadPool(1);
    }
}
