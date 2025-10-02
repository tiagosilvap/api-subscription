package com.subscription.infrastructure.persistence.mongo.repository;

import com.subscription.infrastructure.persistence.mongo.document.*;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InvoiceMongoRepo extends MongoRepository<InvoiceDocument, String> {
}
