package com.subscription.infrastructure.persistence.mongo.mapper.subscription;

import com.subscription.domain.common.Money;
import com.subscription.domain.model.priceversion.PriceVersionId;
import com.subscription.domain.model.plan.PlanId;
import com.subscription.domain.model.subscription.PricingTerms;
import com.subscription.infrastructure.persistence.mongo.document.subscription.PricingTermsDocument;

import java.util.UUID;

public class PricingTermsMapper {

    public static PricingTermsDocument toDoc(PricingTerms pricingTerms) {
        return new PricingTermsDocument(
                pricingTerms.planId().value().toString(),
                pricingTerms.priceVersionId().value().toString(),
                pricingTerms.amount().asBigDecimal(),
                pricingTerms.effectiveFrom()
        );
    }

    public static PricingTerms toDomain(PricingTermsDocument doc) {
        return new PricingTerms(
                new PlanId(UUID.fromString(doc.planId())),
                new PriceVersionId(UUID.fromString(doc.priceVersionId())),
                Money.of(doc.amount()),
                doc.effectiveFrom());
    }
}
