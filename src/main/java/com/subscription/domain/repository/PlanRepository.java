package com.subscription.domain.repository;

import com.subscription.domain.model.plan.*;
import com.subscription.domain.model.subscription.Subscription;

import java.util.Optional;

public interface PlanRepository {
    void save(Plan plan);
    Optional<Plan> findById(PlanId id);
}

