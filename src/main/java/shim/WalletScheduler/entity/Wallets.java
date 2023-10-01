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

    private BigDecimal balances;

    public static Wallets createWallets(WalletQueues queue) {
        Wallets wallet = new Wallets();
        wallet.setBalances(queue.getBalances());
        wallet.setWallet_id(queue.getWalletId());
        return wallet;
    }
    public void changeBalance(Wallets wallets) {
        this.balances.add(wallets.getBalances());
    }

}
