package com.subscription.infrastructure.persistence.jpa.entity;

import com.subscription.domain.model.invoice.InvoiceStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "invoices")
@Getter
@Setter
public class InvoiceEntity {
    @Id
    private UUID id;
    private UUID subscriptionId;
    private BigDecimal amount;
    private LocalDate dueDate;
    @Enumerated(EnumType.STRING)
    private InvoiceStatus status;
}

