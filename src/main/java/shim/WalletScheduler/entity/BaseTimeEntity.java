package shim.WalletScheduler.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@MappedSuperclass
@Getter @Setter
public class BaseTimeEntity {
    @Column(updatable = false)
    private LocalDateTime created_at;

    private LocalDateTime updated_at;
}
