package com.subscription.infrastructure.persistence.mongo.mapper;

import com.subscription.domain.model.customer.CustomerId;
import com.subscription.domain.model.invoice.*;
import com.subscription.infrastructure.persistence.mongo.document.InvoiceDocument;

import java.util.UUID;

public final class InvoiceMapper {

    public static InvoiceDocument toDoc(Invoice invoice) {
        var document = new InvoiceDocument();
        document.setId(invoice.id().value().toString());
        document.setSubscriptionId(invoice.getSubscriptionId().value().toString());
        document.setPlanId(invoice.getPlanId().value().toString());
        document.setPriceVersionId(invoice.getPriceVersionId().value().toString());
        document.setAmount(invoice.getAmount().asBigDecimal());
        document.setDueDate(invoice.getDueDate());
        document.setStatus(invoice.getStatus().name());
        return document;
    }
}
