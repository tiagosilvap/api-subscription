package com.subscription.domain.common;

import java.math.BigDecimal;

public record Money(BigDecimal amount) {

    public Money(BigDecimal amount) {
        if (amount == null || amount.scale() > 2 || amount.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Invalid money amount");
        this.amount = amount.setScale(2);
    }

    public static Money of(BigDecimal value) {
        return new Money(value);
    }

    public BigDecimal asBigDecimal() {
        return amount;
    }
}
