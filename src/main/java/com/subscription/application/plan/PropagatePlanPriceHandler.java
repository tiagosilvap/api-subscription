package com.subscription.application.plan;

import com.subscription.domain.model.plan.Plan;
import com.subscription.domain.event.plan.PlanPriceChangedEvent;
import com.subscription.domain.repository.PlanRepository;
import com.subscription.domain.repository.SubscriptionRepository;
import com.subscription.domain.model.subscription.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PropagatePlanPriceHandler {

    private final SubscriptionRepository subscriptionRepository;
    private final PlanRepository planRepository;

    public PropagatePlanPriceHandler(SubscriptionRepository subscriptionRepository, PlanRepository planRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.planRepository = planRepository;
    }

    @Transactional
    public void handle(PlanPriceChangedEvent e) {
        Plan plan = planRepository.findById(e.planId()).orElseThrow();
        for (Subscription subscription : subscriptionRepository.findByPlanId(plan.getId())) {
            subscription.updateSubscriptionByNewPriceVersion(plan);
            subscriptionRepository.save(subscription);
        }
    }
}
