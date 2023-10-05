package shim.WalletScheduler.repository;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shim.WalletScheduler.entity.Wallets;

import java.util.Optional;

public interface WalletsRepository extends JpaRepository<Wallets, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select w from Wallets w where w.wallet_id = :wallet_id")
    Optional<Wallets> findByIdForUpdate(@Param("wallet_id") Long id);
}
