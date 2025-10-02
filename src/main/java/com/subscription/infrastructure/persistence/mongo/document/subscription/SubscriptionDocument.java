package com.subscription.infrastructure.persistence.mongo.document.subscription;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "subscriptions")
@Getter
@Setter
public class SubscriptionDocument {
    @Id
    private String id;
    private String customerId;
    private PricingTermsDocument pricingTerms;
    private String status;
    private LocalDate startedAt;
    private LocalDate canceledAt;
    private List<SubscriptionChangeDocument> changes;
}
