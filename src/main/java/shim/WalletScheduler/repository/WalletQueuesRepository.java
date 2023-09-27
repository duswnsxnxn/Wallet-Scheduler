package shim.WalletScheduler.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import shim.WalletScheduler.entity.WalletQueues;

import java.util.List;

public interface WalletQueuesRepository extends JpaRepository<WalletQueues, Long> {

//    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<WalletQueues> getWalletQueuesBy(Pageable pageable);

}
