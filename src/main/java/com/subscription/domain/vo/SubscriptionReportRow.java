package com.subscription.domain.vo;

import com.subscription.domain.model.subscription.SubscriptionStatus;

import java.math.BigDecimal;
import java.util.List;

public record SubscriptionReportRow(
        String subscriptionId,
        String customerName,
        String planName,
        SubscriptionStatus status,
        BigDecimal amount,
        List<BigDecimal> invoicesAmount
) {}
