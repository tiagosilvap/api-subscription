package com.subscription.infrastructure.persistence.jpa.repository;

import com.subscription.infrastructure.persistence.jpa.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InvoiceJpaRepo extends JpaRepository<InvoiceEntity, UUID> {
}


