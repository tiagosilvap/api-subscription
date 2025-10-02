package com.subscription.infrastructure.persistence.mapper;

import com.subscription.domain.model.customer.Customer;
import com.subscription.domain.model.customer.CustomerId;
import com.subscription.domain.model.invoice.Invoice;
import com.subscription.domain.model.plan.PlanId;
import com.subscription.domain.model.subscription.Subscription;
import com.subscription.domain.model.subscription.SubscriptionId;
import com.subscription.infrastructure.persistence.jpa.entity.CustomerEntity;
import com.subscription.infrastructure.persistence.jpa.entity.InvoiceEntity;
import com.subscription.infrastructure.persistence.jpa.entity.SubscriptionEntity;

public final class CustomerMapper {
    private CustomerMapper() {
    }

    public static Customer toDomain(CustomerEntity entity) {
        return new Customer(new CustomerId(entity.getId()), entity.getName(), entity.getEmail());
    }
}

