package com.subscription.infrastructure.event.listener;

import com.subscription.application.plan.PropagatePlanPriceHandler;
import com.subscription.domain.event.plan.PlanPriceChangedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SpringPlanPriceChangedListener {

    private final PropagatePlanPriceHandler handler;

    public SpringPlanPriceChangedListener(PropagatePlanPriceHandler handler) {
        this.handler = handler;
    }

    @EventListener
    public void on(PlanPriceChangedEvent event) {
        handler.handle(event);
    }
}