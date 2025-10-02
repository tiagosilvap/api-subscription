package com.subscription.application.subscription.report;

import java.math.BigDecimal;
import java.util.List;

import com.subscription.domain.model.subscription.SubscriptionStatus;

public record GenerateSubscriptionReportResponse(
        String subscriptionId,
        String customerName,
        String planName,
        SubscriptionStatus status,
        BigDecimal amount,
        List<BigDecimal> invoicesAmount) {
}
