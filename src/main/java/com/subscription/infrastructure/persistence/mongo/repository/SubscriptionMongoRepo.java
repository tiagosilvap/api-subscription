package com.subscription.infrastructure.persistence.mongo.repository;

import com.subscription.infrastructure.persistence.mongo.document.subscription.SubscriptionDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SubscriptionMongoRepo extends MongoRepository<SubscriptionDocument, String> {
    List<SubscriptionDocument> findByPricingTerms_PlanId(String pricingTermsPlanId);
}