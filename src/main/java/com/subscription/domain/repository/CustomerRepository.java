package com.subscription.domain.repository;

import com.subscription.domain.model.customer.*;

import java.util.Optional;

public interface CustomerRepository {
    Optional<Customer> findById(CustomerId id);
}

