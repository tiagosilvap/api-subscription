package com.subscription.infrastructure.persistence.mapper;

import com.subscription.domain.model.invoice.*;
import com.subscription.domain.model.subscription.*;
import com.subscription.infrastructure.persistence.jpa.entity.*;

public final class InvoiceMapper {
    private InvoiceMapper() {
    }

    public static InvoiceEntity toEntity(Invoice invoice) {
        var e = new InvoiceEntity();
        e.setId(invoice.id().value());
        e.setSubscriptionId(invoice.getSubscriptionId().value());
        e.setAmount(invoice.getAmount().asBigDecimal());
        e.setDueDate(invoice.getDueDate());
        e.setStatus(invoice.getStatus());
        return e;
    }
}

