package com.subscription.application.plan.usecase;

import com.subscription.domain.model.plan.Plan;
import com.subscription.domain.model.plan.PlanId;
import com.subscription.domain.model.subscription.Subscription;
import com.subscription.domain.model.subscription.SubscriptionId;
import com.subscription.domain.repository.PlanRepository;
import com.subscription.domain.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChangeSubscriptionPlanUseCase {

    private final SubscriptionRepository subRepo;
    private final PlanRepository planRepo;

    public ChangeSubscriptionPlanUseCase(SubscriptionRepository subRepo, PlanRepository planRepo) {
        this.subRepo = subRepo;
        this.planRepo = planRepo;
    }

    @Transactional
    public void handle(SubscriptionId subId, PlanId newPlanId) {
        Subscription s = subRepo.findById(subId).orElseThrow();
        Plan newPlan = planRepo.findById(newPlanId).orElseThrow();
        s.changePlan(newPlan);
        subRepo.save(s);
    }
}
