package com.subscription.infrastructure.persistence.mapper;

import com.subscription.domain.model.customer.CustomerId;
import com.subscription.domain.model.invoice.Invoice;
import com.subscription.domain.model.plan.PlanId;
import com.subscription.domain.model.subscription.Subscription;
import com.subscription.domain.model.subscription.SubscriptionId;
import com.subscription.infrastructure.persistence.jpa.entity.CustomerEntity;
import com.subscription.infrastructure.persistence.jpa.entity.InvoiceEntity;
import com.subscription.infrastructure.persistence.jpa.entity.PlanEntity;
import com.subscription.infrastructure.persistence.jpa.entity.SubscriptionEntity;

public final class SubscriptionMapper {
    private SubscriptionMapper() {
    }

    public static SubscriptionEntity toEntity(
            Subscription subscription,
            PlanEntity planEntity,
            CustomerEntity customerEntity) {
        SubscriptionEntity e = new SubscriptionEntity();
        e.setId(subscription.id().value());
        e.setCustomer(customerEntity);
        e.setPlan(planEntity);
        e.setStatus(subscription.status());
        return e;
    }

    public static Subscription toDomain(SubscriptionEntity e) {
        return Subscription.get(
                new SubscriptionId(e.getId()),
                new CustomerId(e.getCustomer().getId()),
                new PlanId(e.getPlan().getId()),
                e.getStatus(),
                java.time.LocalDate.now(),
                e.getCanceledAt());
    }
}

