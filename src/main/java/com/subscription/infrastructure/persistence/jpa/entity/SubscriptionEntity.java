package com.subscription.infrastructure.persistence.jpa.entity;

import com.subscription.domain.model.subscription.SubscriptionStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "subscriptions")
@Getter
@Setter
public class SubscriptionEntity {
    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customers", nullable = false)
    private CustomerEntity customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plans", nullable = false)
    private PlanEntity plan;

    @Enumerated(EnumType.STRING)
    private SubscriptionStatus status;

    private LocalDate startedAt;

    private LocalDate canceledAt;
}

