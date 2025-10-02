package com.subscription.infrastructure.persistence.jpa.repository;

import com.subscription.infrastructure.persistence.jpa.entity.*;
import org.springframework.data.jpa.repository.*;

import java.util.UUID;

public interface SubscriptionJpaRepo extends JpaRepository<SubscriptionEntity, UUID> {
}


