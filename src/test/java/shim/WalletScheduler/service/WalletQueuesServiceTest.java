package shim.WalletScheduler.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import shim.WalletScheduler.entity.WalletQueues;
import shim.WalletScheduler.entity.Wallets;
import shim.WalletScheduler.repository.WalletQueuesRepository;
import shim.WalletScheduler.repository.WalletsRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(properties = {"spring.profiles.active=test"})
class WalletQueuesServiceTest {

    @Autowired
    private WalletQueuesService walletQueuesService;

    @Autowired
    private WalletQueuesRepository queuesRepository;

    @Autowired
    private WalletsRepository walletsRepository;

    private final int cnt = 10;

    @BeforeEach
    void insertData() {

        int sum = 0;

        for (int i = 0; i < cnt; i++) {
            WalletQueues queue = new WalletQueues();
            queue.setWalletId(1L);
            queue.setBalances(new BigDecimal(i));
            queuesRepository.save(queue);
            sum += i;
        }

        System.out.println("총 합계" + sum);
    }

    @DisplayName("큐테이블에서 잘 가져오는지 확인")
    @Test
    public void t1() throws Exception {
        List<WalletQueues> result = walletQueuesService.getWalletQueues();
        assertThat(result.size()).isEqualTo(cnt);

    }

    @DisplayName("calc()메소드 작동 테스트")
    @Test
    public void t2() throws Exception {

        walletQueuesService.calc();
        Wallets wallet = walletsRepository.findById(1L).orElse(null);

        assertThat(wallet.getBalances()).isEqualTo(new BigDecimal(45));

//        assertThat(queuesRepository.findAll().size()).isEqualTo(0);

    }
}