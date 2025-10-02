package com.subscription.domain.model.plan;

import com.subscription.domain.common.Money;

public record Plan(PlanId id, String name, Money price) {
}