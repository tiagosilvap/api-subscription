package com.subscription.application.subscription.create;

import java.util.UUID;
import java.time.LocalDate;

import com.subscription.domain.model.subscription.SubscriptionStatus;

public record CreateSubscriptionResponse(
        UUID id,
        UUID customerId,
        UUID planId,
        SubscriptionStatus status,
        LocalDate startedAt) {
}

