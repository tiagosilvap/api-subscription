package com.subscription.infrastructure.persistence.mongo.adapter;

import com.subscription.domain.model.customer.Customer;
import com.subscription.domain.model.customer.CustomerId;
import com.subscription.domain.repository.CustomerRepository;
import com.subscription.infrastructure.persistence.mongo.mapper.CustomerMapper;
import com.subscription.infrastructure.persistence.mongo.repository.CustomerMongoRepo;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class CustomerRepositoryAdapter implements CustomerRepository {

    private final CustomerMongoRepo customerMongoRepo;

    public CustomerRepositoryAdapter(CustomerMongoRepo customerMongoRepo) {
        this.customerMongoRepo = customerMongoRepo;
    }

    @Override
    public Optional<Customer> findById(CustomerId customerId) {
        return customerMongoRepo.findById(customerId.value().toString()).map(CustomerMapper::toDomain);
    }
}

