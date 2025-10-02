package com.subscription.application.dto;

import java.util.UUID;
import java.time.LocalDate;

import com.subscription.domain.model.subscription.SubscriptionStatus;

public record SubscriptionView(
        UUID id,
        UUID customerId,
        UUID planId,
        SubscriptionStatus status,
        LocalDate startedAt) {
}

