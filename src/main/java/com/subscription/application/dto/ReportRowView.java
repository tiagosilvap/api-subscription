package com.subscription.application.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.subscription.domain.model.subscription.SubscriptionStatus;

public record ReportRowView(
        UUID subscriptionId,
        String customerName,
        String planName,
        SubscriptionStatus status,
        BigDecimal currentInvoiceAmount) {
}
