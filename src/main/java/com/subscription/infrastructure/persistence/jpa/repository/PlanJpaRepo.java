package com.subscription.infrastructure.persistence.jpa.repository;

import com.subscription.infrastructure.persistence.jpa.entity.PlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PlanJpaRepo extends JpaRepository<PlanEntity, UUID> {
}


