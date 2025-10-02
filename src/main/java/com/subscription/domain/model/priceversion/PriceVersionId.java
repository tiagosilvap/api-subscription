package com.subscription.domain.model.priceversion;

import java.util.UUID;

public record PriceVersionId(UUID value) {
    public static PriceVersionId newId() {
        return new PriceVersionId(UUID.randomUUID());
    }
}

