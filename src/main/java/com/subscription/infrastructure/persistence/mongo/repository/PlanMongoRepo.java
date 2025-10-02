package com.subscription.infrastructure.persistence.mongo.repository;

import com.subscription.infrastructure.persistence.mongo.document.plan.PlanDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlanMongoRepo extends MongoRepository<PlanDocument, String> {
}
