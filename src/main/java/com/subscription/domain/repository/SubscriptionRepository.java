package com.subscription.domain.repository;

import com.subscription.domain.model.subscription.*;

import java.util.Optional;

public interface SubscriptionRepository {
    void save(Subscription s);
    Optional<Subscription> findById(SubscriptionId id);
}

