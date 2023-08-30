package shim.WalletScheduler.repository;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import shim.WalletScheduler.entity.WalletQueues;

import java.util.List;

public interface WalletQueuesRepository extends JpaRepository<WalletQueues, Long> {

    @Lock(LockModeType.PESSIMISTIC_READ)
    List<WalletQueues> findTop100By();

}
