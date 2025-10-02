package com.subscription.infrastructure.persistence.adapter;

import com.subscription.domain.model.subscription.*;
import com.subscription.domain.repository.SubscriptionRepository;
import com.subscription.infrastructure.persistence.jpa.repository.CustomerJpaRepo;
import com.subscription.infrastructure.persistence.jpa.repository.PlanJpaRepo;
import com.subscription.infrastructure.persistence.jpa.repository.SubscriptionJpaRepo;
import com.subscription.infrastructure.persistence.mapper.SubscriptionMapper;
import org.springframework.stereotype.Repository;

import static com.subscription.infrastructure.persistence.mapper.SubscriptionMapper.*;

import java.util.Optional;

@Repository
public class SubscriptionRepositoryAdapter implements SubscriptionRepository {
    private final SubscriptionJpaRepo subscriptionJpaRepo;
    private final PlanJpaRepo planJpaRepo;
    private final CustomerJpaRepo customerJpaRepo;

    public SubscriptionRepositoryAdapter(
            SubscriptionJpaRepo subscriptionJpaRepo,
            PlanJpaRepo planJpaRepo,
            CustomerJpaRepo customerJpaRepo) {
        this.subscriptionJpaRepo = subscriptionJpaRepo;
        this.planJpaRepo = planJpaRepo;
        this.customerJpaRepo = customerJpaRepo;
    }

    @Override
    public void save(Subscription s) {
        var planId = s.planId().value();
        var planEntity = planJpaRepo.findById(planId)
                .orElseThrow(() -> new IllegalStateException("PlanEntity not found for planId: " + planId));

        var customerId = s.customerId().value();
        var customerEntity = customerJpaRepo.findById(customerId)
                .orElseThrow(() -> new IllegalStateException("CustomerEntity not found for planId: " + customerId));

        subscriptionJpaRepo.save(toEntity(s, planEntity, customerEntity));
    }

    @Override
    public Optional<Subscription> findById(SubscriptionId id) {
        return subscriptionJpaRepo.findById(id.value()).map(SubscriptionMapper::toDomain);
    }
}

