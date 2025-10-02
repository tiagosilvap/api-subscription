package com.subscription.domain.repository;

import com.subscription.domain.model.plan.*;

import java.util.Optional;

public interface PlanRepository {
    Optional<Plan> findById(PlanId id);
}

