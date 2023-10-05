package shim.WalletScheduler.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter @Setter
public class Wallets extends BaseTimeEntity{

    @Id
    private Long wallet_id;

    private BigDecimal balance;

    public static Wallets createWallets(WalletQueues queue) {
        Wallets wallet = new Wallets();
        wallet.setBalance(queue.getBalances());
        wallet.setWallet_id(queue.getWalletId());
        return wallet;
    }

    public void changeBalance(WalletQueues queues) {
        balance = balance.add(queues.getBalances());
    }

}
