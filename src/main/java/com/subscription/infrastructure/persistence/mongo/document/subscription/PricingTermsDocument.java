package com.subscription.infrastructure.persistence.mongo.document.subscription;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PricingTermsDocument(
        String planId,
        String priceVersionId,
        BigDecimal amount,
        LocalDate effectiveFrom) {}
