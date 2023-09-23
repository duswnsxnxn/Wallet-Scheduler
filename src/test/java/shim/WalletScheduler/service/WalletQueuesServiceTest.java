package shim.WalletScheduler.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class WalletQueuesServiceTest {

    @Autowired
    private WalletQueuesService walletQueuesService;

    @DisplayName("큐테이블에서 잘 가져오는지 확인")
    @Test
    public void t1() throws Exception {
        walletQueuesService.getWalletQueues();
    }

    @DisplayName("스케쥴러 작동 테스트")
    @Test
    public void t2() throws Exception {
        walletQueuesService.calc();
        Assertions.assertThat(walletQueuesService.getWalletQueues().size())
                .isEqualTo(0);
    }
}