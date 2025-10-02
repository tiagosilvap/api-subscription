package com.subscription.infrastructure.persistence.mongo.mapper;

import com.subscription.domain.model.customer.Customer;
import com.subscription.domain.model.customer.CustomerId;
import com.subscription.infrastructure.persistence.mongo.document.CustomerDocument;

import java.util.UUID;

public class CustomerMapper {
    public static Customer toDomain(CustomerDocument doc) {
        return new Customer(toCustomerId(doc.getId()), doc.getName(), doc.getEmail());
    }

    private static CustomerId toCustomerId(String id) {
        return new CustomerId(UUID.fromString(id));
    }


}
