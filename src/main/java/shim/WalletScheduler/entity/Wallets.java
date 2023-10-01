package shim.WalletScheduler.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter @Setter
public class Wallets extends BaseTimeEntity{

    @Id
    private Long wallet_id;

    private BigDecimal balances;

    @Version
    private int version;

}
