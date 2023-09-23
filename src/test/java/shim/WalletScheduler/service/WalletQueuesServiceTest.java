package shim.WalletScheduler.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import shim.WalletScheduler.entity.WalletQueues;
import shim.WalletScheduler.entity.Wallets;
import shim.WalletScheduler.repository.WalletQueuesRepository;
import shim.WalletScheduler.repository.WalletsRepository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
class WalletQueuesServiceTest {

    @InjectMocks
    private WalletQueuesService walletQueuesService;

    @Mock
    private WalletQueuesRepository queuesRepository;

    @Mock
    private WalletsRepository walletsRepository;

    @DisplayName("큐테이블에서 잘 가져오는지 확인")
    @Test
    public void t1() throws Exception {
        walletQueuesService.getWalletQueues();
    }

    @DisplayName("calc()메소드 작동 테스트")
    @Test
    public void t2() throws Exception {

        WalletQueues queue = new WalletQueues();
        queue.setBalances(new BigDecimal(100));
        queue.setWalletId(1L);

        when(queuesRepository.findTop100By()).thenReturn(Arrays.asList(queue));

        Wallets wallets = new Wallets();
        wallets.setBalances(new BigDecimal(200));

        when(walletsRepository.findById(anyLong())).thenReturn(Optional.of(wallets));
        walletQueuesService.calc();

        verify(walletsRepository, times(1)).save(any(Wallets.class));
        verify(queuesRepository, times(1)).delete(any(WalletQueues.class));
    }
}