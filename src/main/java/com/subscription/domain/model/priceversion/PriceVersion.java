package com.subscription.domain.model.priceversion;

import com.subscription.domain.common.Money;

import java.time.LocalDate;

public final class PriceVersion {

    private final PriceVersionId id;
    private final Money amount;
    private final LocalDate createdAt;

    public PriceVersion(PriceVersionId id, Money amount, LocalDate createdAt) {
        this.id = id;
        this.amount = amount;
        this.createdAt = createdAt;
    }

    public PriceVersionId getId() {
        return id;
    }

    public Money getAmount() {
        return amount;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }
}