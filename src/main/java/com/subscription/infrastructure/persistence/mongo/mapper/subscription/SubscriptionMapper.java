package com.subscription.infrastructure.persistence.mongo.mapper.subscription;

import com.subscription.domain.model.customer.CustomerId;
import com.subscription.domain.model.subscription.*;
import com.subscription.infrastructure.persistence.mongo.document.subscription.SubscriptionChangeDocument;
import com.subscription.infrastructure.persistence.mongo.document.subscription.SubscriptionDocument;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class SubscriptionMapper {

    public static SubscriptionDocument toDoc(Subscription subscription) {
        var document = new SubscriptionDocument();
        document.setId(subscription.getId().value().toString());
        document.setCustomerId(subscription.getCustomerId().value().toString());
        document.setPricingTerms(PricingTermsMapper.toDoc(subscription.getPricingTerms()));
        document.setStatus(subscription.getStatus().name());
        document.setStartedAt(subscription.getStartedAt());
        document.setCanceledAt(subscription.getCanceledAt());
        document.setChanges(mapSubscriptionChangeToDocuments(subscription.getChanges()));
        return document;
    }

    public static Subscription toDomain(SubscriptionDocument document) {
        return Subscription.from(
                new SubscriptionId(UUID.fromString(document.getId())),
                new CustomerId(UUID.fromString(document.getCustomerId())),
                PricingTermsMapper.toDomain(document.getPricingTerms()),
                SubscriptionStatus.valueOf(document.getStatus()),
                document.getStartedAt(),
                document.getCanceledAt(),
                mapSubscriptionChanges(document.getChanges())
        );
    }

    private static List<SubscriptionChange> mapSubscriptionChanges(
            List<SubscriptionChangeDocument> documents) {
        return documents.stream()
                .map(SubscriptionChangeMapper::toDomain)
                .collect(Collectors.toList());
    }

    private static List<SubscriptionChangeDocument> mapSubscriptionChangeToDocuments(
            List<SubscriptionChange> subscriptionChanges) {
        return subscriptionChanges.stream()
                .map(SubscriptionChangeMapper::toDoc)
                .collect(Collectors.toList());
    }


}
