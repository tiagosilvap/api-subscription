package com.subscription.domain.model.invoice;

import com.subscription.domain.common.Money;
import com.subscription.domain.model.subscription.SubscriptionId;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
public class Invoice {
    private final InvoiceId id;
    private final SubscriptionId subscriptionId;
    private final Money amount;
    private final LocalDate dueDate;
    private InvoiceStatus status;

    private Invoice(
            InvoiceId id,
            SubscriptionId subscriptionId,
            Money amount,
            LocalDate dueDate,
            InvoiceStatus status) {
        this.id = id;
        this.subscriptionId = subscriptionId;
        this.amount = amount;
        this.dueDate = dueDate;
        this.status = status;
    }

    public static Invoice create(
            SubscriptionId subId,
            Money amount,
            LocalDate dueDate,
            InvoiceStatus status) {
        return new Invoice(new InvoiceId(UUID.randomUUID()), subId, amount, dueDate, status);
    }

    public InvoiceId id() {
        return id;
    }
}
