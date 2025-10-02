package com.subscription.infrastructure.persistence.mapper;

import com.subscription.domain.common.Money;
import com.subscription.domain.model.customer.Customer;
import com.subscription.domain.model.customer.CustomerId;
import com.subscription.domain.model.plan.Plan;
import com.subscription.domain.model.plan.PlanId;
import com.subscription.infrastructure.persistence.jpa.entity.CustomerEntity;
import com.subscription.infrastructure.persistence.jpa.entity.PlanEntity;

public final class PlanMapper {
    private PlanMapper() {
    }

    public static Plan toDomain(PlanEntity entity) {
        return new Plan(new PlanId(entity.getId()), entity.getName(), Money.of(entity.getPrice()));
    }
}

