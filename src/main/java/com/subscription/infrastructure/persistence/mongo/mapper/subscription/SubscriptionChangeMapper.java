package com.subscription.infrastructure.persistence.mongo.mapper.subscription;

import com.subscription.domain.common.Money;
import com.subscription.domain.model.priceversion.PriceVersionId;
import com.subscription.domain.model.plan.PlanId;
import com.subscription.domain.model.subscription.SubscriptionChange;
import com.subscription.infrastructure.persistence.mongo.document.subscription.SubscriptionChangeDocument;

import java.util.UUID;

public class SubscriptionChangeMapper {
    public static SubscriptionChangeDocument toDoc(SubscriptionChange subscriptionChange) {
        return new SubscriptionChangeDocument(
                subscriptionChange.at(),
                subscriptionChange.type(),
                subscriptionChange.planId().value().toString(),
                subscriptionChange.priceVersionId().value().toString(),
                subscriptionChange.amount().asBigDecimal()
        );
    }

    public static SubscriptionChange toDomain(SubscriptionChangeDocument doc) {
        return new SubscriptionChange(
                doc.at(),
                doc.type(),
                new PlanId(UUID.fromString(doc.planId())),
                new PriceVersionId(UUID.fromString(doc.priceVersionId())),
                Money.of(doc.amount())
        );
    }
}
