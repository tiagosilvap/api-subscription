package com.subscription.infrastructure.persistence.mongo.mapper.plan;

import com.subscription.domain.model.priceversion.PriceVersion;
import com.subscription.domain.model.priceversion.PriceVersionId;
import com.subscription.domain.model.plan.Plan;
import com.subscription.domain.model.plan.PlanId;
import com.subscription.infrastructure.persistence.mongo.document.plan.PlanDocument;
import com.subscription.infrastructure.persistence.mongo.document.plan.PriceVersionDocument;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PlanMapper {
    public static Plan toDomain(PlanDocument doc) {
        return new Plan(
                new PlanId(UUID.fromString(doc.getId())),
                doc.getName(),
                mapPriceVersions(doc.getVersions()),
                new PriceVersionId(UUID.fromString(doc.getCurrentVersionId())));
    }

    public static PlanDocument toDoc(Plan plan) {
        var document = new PlanDocument();
        document.setId(plan.getId().value().toString());
        document.setName(plan.getName());
        document.setVersions(mapPriceVersionToDocuments(plan.getVersions()));
        document.setCurrentVersionId(plan.currentVersion().getId().value().toString());
        return document;
    }

    private static List<PriceVersion> mapPriceVersions(List<PriceVersionDocument> documents) {
        return documents.stream()
                .map(PriceVersionMapper::toDomain)
                .collect(Collectors.toList());
    }

    private static List<PriceVersionDocument> mapPriceVersionToDocuments(List<PriceVersion> priceVersions) {
        return priceVersions.stream()
                .map(PriceVersionMapper::toDoc)
                .collect(Collectors.toList());
    }
}
