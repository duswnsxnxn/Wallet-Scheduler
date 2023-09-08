package shim.WalletScheduler.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shim.WalletScheduler.entity.WalletQueues;
import shim.WalletScheduler.repository.WalletQueuesRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WalletQueuesService {

    private final WalletQueuesRepository queuesRepository;

    @Transactional(readOnly = true)
    public List<WalletQueues> getWalletQueues() {

            return queuesRepository.findTop100By();
    }
}
