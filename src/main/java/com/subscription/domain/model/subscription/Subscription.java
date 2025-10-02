package com.subscription.domain.model.subscription;

import com.subscription.domain.common.Money;
import com.subscription.domain.model.customer.CustomerId;
import com.subscription.domain.model.plan.PlanId;
import com.subscription.domain.model.invoice.Invoice;
import com.subscription.domain.model.invoice.InvoiceStatus;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Optional;

@Getter
public class Subscription {
    private final SubscriptionId id;
    private final CustomerId customerId;
    private final PlanId planId;
    private SubscriptionStatus status;
    private LocalDate startedAt;
    private LocalDate canceledAt;

    private Subscription(
            SubscriptionId id,
            CustomerId customerId,
            PlanId planId,
            SubscriptionStatus status,
            LocalDate startedAt,
            LocalDate canceledAt) {
        this.id = id;
        this.customerId = customerId;
        this.planId = planId;
        this.status = status;
        this.startedAt = startedAt;
        this.canceledAt = canceledAt;
    }

    public static Subscription get(SubscriptionId id,
                                   CustomerId customerId,
                                   PlanId planId,
                                   SubscriptionStatus status,
                                   LocalDate startedAt,
                                   LocalDate canceledAt) {
        return new Subscription(id, customerId, planId, status, startedAt, canceledAt);
    }

    public static Subscription start(CustomerId customerId, PlanId planId, LocalDate startDate) {
        return new Subscription(
                SubscriptionId.newId(),
                customerId,
                planId,
                SubscriptionStatus.ACTIVE,
                startDate,
                null);
    }

    public Invoice issueFirstInvoice(Money amount, LocalDate dueDate) {
        if (!isActive())
            throw new IllegalStateException("Cannot issue invoice for inactive subscription");
        return Invoice.create(id, amount, dueDate, InvoiceStatus.OPEN);
    }

    public void cancel(LocalDate when) {
        if (status == SubscriptionStatus.CANCELED)
            return;
        this.status = SubscriptionStatus.CANCELED;
        this.canceledAt = when;
    }

    public boolean isActive() {
        return status == SubscriptionStatus.ACTIVE;
    }

    public SubscriptionId id() {
        return id;
    }

    public CustomerId customerId() {
        return customerId;
    }

    public PlanId planId() {
        return planId;
    }

    public SubscriptionStatus status() {
        return status;
    }

    public Optional<LocalDate> canceledAt() {
        return Optional.ofNullable(canceledAt);
    }
}
