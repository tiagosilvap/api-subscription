package com.subscription.infrastructure.persistence.adapter;

import com.subscription.domain.model.customer.Customer;
import com.subscription.domain.model.customer.CustomerId;
import com.subscription.domain.repository.CustomerRepository;
import com.subscription.infrastructure.persistence.jpa.repository.CustomerJpaRepo;
import com.subscription.infrastructure.persistence.mapper.CustomerMapper;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;

@Repository
public class CustomerRepositoryAdapter implements CustomerRepository {
    private final CustomerJpaRepo jpa;

    public CustomerRepositoryAdapter(CustomerJpaRepo jpa) {
        this.jpa = jpa;
    }

    @Override
    public Optional<Customer> findById(CustomerId customerId) {
        return jpa.findById(customerId.value()).map(CustomerMapper::toDomain);
    }
}

