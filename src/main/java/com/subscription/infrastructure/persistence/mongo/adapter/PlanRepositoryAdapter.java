package com.subscription.infrastructure.persistence.mongo.adapter;

import com.subscription.domain.model.plan.Plan;
import com.subscription.domain.model.plan.PlanId;
import com.subscription.domain.repository.PlanRepository;
import com.subscription.infrastructure.persistence.mongo.mapper.plan.PlanMapper;
import com.subscription.infrastructure.persistence.mongo.mapper.subscription.SubscriptionMapper;
import com.subscription.infrastructure.persistence.mongo.repository.PlanMongoRepo;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PlanRepositoryAdapter implements PlanRepository {

    private final PlanMongoRepo planMongoRepo;

    public PlanRepositoryAdapter(PlanMongoRepo planMongoRepo) {
        this.planMongoRepo = planMongoRepo;
    }

    @Override
    public Optional<Plan> findById(PlanId planId) {
        return planMongoRepo.findById(planId.value().toString()).map(PlanMapper::toDomain);
    }

    @Override
    public void save(Plan plan) {
        planMongoRepo.save(PlanMapper.toDoc(plan));
    }
}

