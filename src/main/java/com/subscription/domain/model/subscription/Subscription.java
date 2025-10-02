package com.subscription.domain.model.subscription;

import com.subscription.domain.model.priceversion.PriceVersion;
import com.subscription.domain.model.customer.CustomerId;
import com.subscription.domain.model.plan.Plan;
import com.subscription.domain.model.invoice.Invoice;
import com.subscription.domain.model.invoice.InvoiceStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Subscription {
    private final SubscriptionId id;
    private final CustomerId customerId;
    private PricingTerms pricingTerms;
    private SubscriptionStatus status;
    private LocalDate startedAt;
    private LocalDate canceledAt;
    private final List<SubscriptionChange> changes = new ArrayList<>();

    private Subscription(
            SubscriptionId id,
            CustomerId customerId,
            PricingTerms pricingTerms,
            SubscriptionStatus status,
            LocalDate startedAt,
            LocalDate canceledAt) {
        this.id = id;
        this.customerId = customerId;
        this.pricingTerms = pricingTerms;
        this.status = status;
        this.startedAt = startedAt;
        this.canceledAt = canceledAt;
    }

    public static Subscription from(SubscriptionId id,
                                    CustomerId customerId,
                                    PricingTerms pricingTerms,
                                    SubscriptionStatus status,
                                    LocalDate startedAt,
                                    LocalDate canceledAt,
                                    List<SubscriptionChange> changes) {
        var subscription = new Subscription(id, customerId, pricingTerms, status, startedAt, canceledAt);
        subscription.changes.addAll(changes);
        return subscription;
    }

    public static Subscription start(CustomerId customerId, Plan plan) {

        var priceVersion = plan.currentVersion();

        var pricingTerms = new PricingTerms(
                plan.getId(),
                priceVersion.getId(),
                priceVersion.getAmount(),
                priceVersion.getCreatedAt()
        );

        var subscription = new Subscription(
                SubscriptionId.newId(),
                customerId,
                pricingTerms,
                SubscriptionStatus.ACTIVE,
                LocalDate.now(),
                null);

        subscription.changes.add(SubscriptionChange.started(
                pricingTerms.planId(), pricingTerms.priceVersionId(), pricingTerms.amount())
        );

        return subscription;
    }

    public void updateSubscriptionByNewPriceVersion(Plan plan) {
        PriceVersion priceVersion = plan.currentVersion();
        if (!priceVersion.getId().equals(pricingTerms.priceVersionId())) {
            this.pricingTerms = new PricingTerms(
                    plan.getId(), priceVersion.getId(), priceVersion.getAmount(), LocalDate.now());

            this.changes.add(SubscriptionChange.priceVersionChanged(
                    plan.getId(), priceVersion.getId(), priceVersion.getAmount(), "PLAN_UPDATE_PROPAGATED"));
        }
    }

    public void changePlan(Plan newPlan) {
        PriceVersion priceVersion = newPlan.currentVersion();
        this.pricingTerms = new PricingTerms(
                newPlan.getId(), priceVersion.getId(), priceVersion.getAmount(), LocalDate.now());

        this.changes.add(SubscriptionChange.planChanged(
                newPlan.getId(), priceVersion.getId(), priceVersion.getAmount()));
    }

    public Invoice issueInvoice(LocalDate dueDate) {
        return Invoice.create(
                id,
                pricingTerms.planId(),
                pricingTerms.priceVersionId(),
                pricingTerms.amount(),
                dueDate,
                InvoiceStatus.OPEN
        );
    }

    public Invoice issueFirstInvoice(LocalDate dueDate) {
        if (!isActive())
            throw new IllegalStateException("Cannot issue invoice for inactive subscription");
        return issueInvoice(dueDate);
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

    public SubscriptionId getId() {
        return id;
    }

    public CustomerId getCustomerId() {
        return customerId;
    }

    public SubscriptionStatus getStatus() {
        return status;
    }

    public PricingTerms getPricingTerms() {
        return pricingTerms;
    }

    public LocalDate getStartedAt() {
        return startedAt;
    }

    public LocalDate getCanceledAt() {
        return canceledAt;
    }

    public List<SubscriptionChange> getChanges() {
        return changes;
    }
}
