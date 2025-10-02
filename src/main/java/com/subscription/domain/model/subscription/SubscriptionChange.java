package com.subscription.domain.model.subscription;

import com.subscription.domain.common.Money;
import com.subscription.domain.model.priceversion.PriceVersionId;
import com.subscription.domain.model.plan.PlanId;

import java.time.LocalDateTime;

public record SubscriptionChange(
        LocalDateTime at, String type, PlanId planId, PriceVersionId priceVersionId, Money amount) {

    public static SubscriptionChange started(
            PlanId planId, PriceVersionId priceVersionId, Money money) {
        return new SubscriptionChange(LocalDateTime.now(), "STARTED", planId, priceVersionId, money);
    }

    public static SubscriptionChange planChanged(
            PlanId planId, PriceVersionId priceVersionId, Money money) {
        return new SubscriptionChange(LocalDateTime.now(), "PLAN_CHANGED", planId, priceVersionId, money);
    }

    public static SubscriptionChange priceVersionChanged(
            PlanId planId, PriceVersionId priceVersionId, Money money, String reason) {
        return new SubscriptionChange(LocalDateTime.now(), reason, planId, priceVersionId, money);
    }
}
