package com.subscription.domain.model.invoice;

import com.subscription.domain.common.Money;
import com.subscription.domain.model.priceversion.PriceVersionId;
import com.subscription.domain.model.plan.PlanId;
import com.subscription.domain.model.subscription.SubscriptionId;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
public class Invoice {
    private final InvoiceId id;
    private final SubscriptionId subscriptionId;
    private PlanId planId;
    private PriceVersionId priceVersionId;
    private final Money amount;
    private final LocalDate dueDate;
    private InvoiceStatus status;

    private Invoice(
            InvoiceId id,
            SubscriptionId subscriptionId,
            PlanId planId,
            PriceVersionId priceVersionId,
            Money amount,
            LocalDate dueDate,
            InvoiceStatus status) {
        this.id = id;
        this.subscriptionId = subscriptionId;
        this.planId = planId;
        this.priceVersionId = priceVersionId;
        this.amount = amount;
        this.dueDate = dueDate;
        this.status = status;
    }

    public static Invoice create(
            SubscriptionId subId,
            PlanId planId,
            PriceVersionId priceVersionId,
            Money amount,
            LocalDate dueDate,
            InvoiceStatus status) {
        return new Invoice(new InvoiceId(UUID.randomUUID()),
                subId, planId,
                priceVersionId,
                amount,
                dueDate,
                status
        );
    }

    public InvoiceId id() {
        return id;
    }
}
