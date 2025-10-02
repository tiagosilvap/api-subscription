package com.subscription.domain.repository;

import com.subscription.domain.model.plan.PlanId;
import com.subscription.domain.model.subscription.*;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository {
    void save(Subscription subscription);
    Optional<Subscription> findById(SubscriptionId id);
    List<Subscription> findByPlanId(PlanId planId);
}

