package com.subscription.infrastructure.persistence.mongo.mapper.plan;

import com.subscription.domain.common.Money;
import com.subscription.domain.model.priceversion.PriceVersion;
import com.subscription.domain.model.priceversion.PriceVersionId;
import com.subscription.infrastructure.persistence.mongo.document.plan.PriceVersionDocument;

import java.util.UUID;

public class PriceVersionMapper {
    public static PriceVersion toDomain(PriceVersionDocument doc) {
        return new PriceVersion(
                new PriceVersionId(UUID.fromString(doc.getId())),
                Money.of(doc.getAmount()),
                doc.getCreatedAt());
    }

    public static PriceVersionDocument toDoc(PriceVersion priceVersion) {
        var document = new PriceVersionDocument();
        document.setId(priceVersion.getId().value().toString());
        document.setAmount(priceVersion.getAmount().asBigDecimal());
        document.setCreatedAt(priceVersion.getCreatedAt());
        return document;
    }
}
