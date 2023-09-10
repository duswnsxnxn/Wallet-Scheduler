package shim.WalletScheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shim.WalletScheduler.entity.Wallets;

public interface WalletsRepository extends JpaRepository<Wallets, Long> {
}
