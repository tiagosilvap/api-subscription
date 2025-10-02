package com.subscription.infrastructure.persistence.adapter;

import com.subscription.domain.model.plan.Plan;
import com.subscription.domain.model.plan.PlanId;
import com.subscription.domain.repository.PlanRepository;
import com.subscription.infrastructure.persistence.jpa.repository.PlanJpaRepo;
import com.subscription.infrastructure.persistence.mapper.PlanMapper;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;

@Repository
public class PlanRepositoryAdapter implements PlanRepository {
    private final PlanJpaRepo jpa;

    public PlanRepositoryAdapter(PlanJpaRepo jpa) {
        this.jpa = jpa;
    }

    @Override
    public Optional<Plan> findById(PlanId planId) {
        return jpa.findById(planId.value()).map(PlanMapper::toDomain);
    }
}

