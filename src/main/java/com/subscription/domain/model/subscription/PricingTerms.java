package com.subscription.domain.model.subscription;

import com.subscription.domain.common.Money;
import com.subscription.domain.model.priceversion.PriceVersionId;
import com.subscription.domain.model.plan.PlanId;

import java.time.LocalDate;

public record PricingTerms(
        PlanId planId,
        PriceVersionId priceVersionId,
        Money amount,
        LocalDate effectiveFrom) {}
