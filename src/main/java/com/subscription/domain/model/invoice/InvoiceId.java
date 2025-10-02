package com.subscription.domain.model.invoice;

import com.subscription.domain.model.subscription.SubscriptionId;

import java.util.UUID;

public record InvoiceId(UUID value) {
    public static InvoiceId newId() {
        return new InvoiceId(UUID.randomUUID());
    }
}
