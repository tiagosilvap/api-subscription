package com.subscription.application.subscription.cancel;

import com.subscription.domain.model.subscription.Subscription;
import com.subscription.domain.model.subscription.SubscriptionId;
import com.subscription.domain.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class CancelSubscriptionUseCase {

    private final SubscriptionRepository subscriptionRepo;

    public CancelSubscriptionUseCase(SubscriptionRepository repo) {
        this.subscriptionRepo = repo;
    }

    @Transactional
    public void handle(UUID subscriptionId) {
        Subscription sub = subscriptionRepo.findById(new SubscriptionId(subscriptionId))
                .orElseThrow(() -> new IllegalArgumentException("Subscription not found"));
        sub.cancel(LocalDate.now());
        subscriptionRepo.save(sub);
    }
}

