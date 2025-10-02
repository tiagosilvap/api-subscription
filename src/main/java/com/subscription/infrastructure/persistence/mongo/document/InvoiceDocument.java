package com.subscription.infrastructure.persistence.mongo.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Document(collection = "invoices")
@Getter
@Setter
public class InvoiceDocument {
    @Id
    private String id;
    private String subscriptionId;
    private String planId;
    private String priceVersionId;
    private BigDecimal amount;
    private LocalDate dueDate;
    private String status;
}