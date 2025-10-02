package com.subscription.application.plan.usecase;

import com.subscription.application.event.DomainEventPublisher;
import com.subscription.domain.common.Money;
import com.subscription.domain.event.plan.PlanPriceChangedEvent;
import com.subscription.domain.model.priceversion.PriceVersion;
import com.subscription.domain.model.plan.Plan;
import com.subscription.domain.model.plan.PlanId;
import com.subscription.domain.repository.PlanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdatePlanPriceUseCase {

    private final PlanRepository planRepo;

    private final DomainEventPublisher publisher;

    public UpdatePlanPriceUseCase(PlanRepository planRepo, DomainEventPublisher publisher) {
        this.planRepo = planRepo;
        this.publisher = publisher;
    }

    @Transactional
    public void handle(PlanId planId, Money newPrice, boolean propagate) {
        Plan plan = planRepo.findById(planId).orElseThrow();
        PriceVersion priceVersion = plan.addNewPrice(newPrice);
        planRepo.save(plan);

        if (propagate) {
            publisher.publish(new PlanPriceChangedEvent(planId, priceVersion.getId()));
        }
    }
}
