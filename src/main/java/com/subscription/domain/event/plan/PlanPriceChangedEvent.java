package com.subscription.domain.event.plan;

import com.subscription.domain.event.DomainEvent;
import com.subscription.domain.model.plan.PlanId;
import com.subscription.domain.model.priceversion.PriceVersionId;

import java.time.Instant;


public record PlanPriceChangedEvent(PlanId planId, PriceVersionId newVersionId, Instant occurredAt)
        implements DomainEvent {

    public PlanPriceChangedEvent(PlanId planId, PriceVersionId newVersionId) {
        this(planId, newVersionId, Instant.now());
    }

    @Override
    public String type() {
        return "PlanPriceChanged";
    }
}