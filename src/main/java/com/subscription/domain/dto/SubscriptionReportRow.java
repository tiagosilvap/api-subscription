package com.subscription.domain.dto;

import com.subscription.domain.model.subscription.SubscriptionStatus;

import java.math.BigDecimal;
import java.util.UUID;

public record SubscriptionReportRow(
        UUID subscriptionId,
        String customerName,
        String planName,
        SubscriptionStatus status,
        BigDecimal currentInvoiceAmount
) {
}
