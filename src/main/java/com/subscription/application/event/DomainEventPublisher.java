package com.subscription.application.event;

import com.subscription.domain.event.DomainEvent;

import java.util.Collection;


public interface DomainEventPublisher {

    void publish(DomainEvent event);

    default void publishAll(Collection<? extends DomainEvent> events) {
        events.forEach(this::publish);
    }
}
