package com.subscription.infrastructure.event.adapter;

import com.subscription.application.event.DomainEventPublisher;
import com.subscription.domain.event.DomainEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class SpringDomainEventPublisherAdapter implements DomainEventPublisher {

    private final ApplicationEventPublisher springPublisher;

    public SpringDomainEventPublisherAdapter(ApplicationEventPublisher springPublisher) {
        this.springPublisher = springPublisher;
    }

    @Override
    public void publish(DomainEvent event) {
        springPublisher.publishEvent(event);
    }
}