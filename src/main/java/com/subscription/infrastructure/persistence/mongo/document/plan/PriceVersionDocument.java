package com.subscription.infrastructure.persistence.mongo.document.plan;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class PriceVersionDocument {
    @Id
    public String id;
    private BigDecimal amount;
    private LocalDate createdAt;
}