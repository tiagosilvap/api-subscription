package com.subscription.infrastructure.persistence.jpa.repository;

import com.subscription.infrastructure.persistence.jpa.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerJpaRepo extends JpaRepository<CustomerEntity, UUID> {
}


