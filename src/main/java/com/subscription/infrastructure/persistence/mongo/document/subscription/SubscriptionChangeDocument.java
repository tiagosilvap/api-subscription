package com.subscription.infrastructure.persistence.mongo.document.subscription;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record SubscriptionChangeDocument(
        LocalDateTime at,
        String type,
        String planId,
        String priceVersionId,
        BigDecimal amount) {}
