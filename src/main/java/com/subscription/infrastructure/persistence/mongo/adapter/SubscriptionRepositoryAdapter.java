package com.subscription.infrastructure.persistence.mongo.adapter;

import com.subscription.domain.model.plan.PlanId;
import com.subscription.domain.model.subscription.*;
import com.subscription.domain.repository.SubscriptionRepository;
import com.subscription.infrastructure.persistence.mongo.mapper.subscription.SubscriptionMapper;
import com.subscription.infrastructure.persistence.mongo.repository.SubscriptionMongoRepo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SubscriptionRepositoryAdapter implements SubscriptionRepository {

    private final SubscriptionMongoRepo subscriptionMongoRepo;

    public SubscriptionRepositoryAdapter(SubscriptionMongoRepo subscriptionMongoRepo) {
        this.subscriptionMongoRepo = subscriptionMongoRepo;
    }

    @Override
    public void save(Subscription subscription) {
        subscriptionMongoRepo.save(SubscriptionMapper.toDoc(subscription));
    }

    @Override
    public Optional<Subscription> findById(SubscriptionId id) {
        return subscriptionMongoRepo.findById(id.value().toString()).map(SubscriptionMapper::toDomain);
    }

    @Override
    public List<Subscription> findByPlanId(PlanId planId) {
        return subscriptionMongoRepo.findByPricingTerms_PlanId(planId.value().toString())
                .stream()
                .map(SubscriptionMapper::toDomain)
                .toList();
    }
}
