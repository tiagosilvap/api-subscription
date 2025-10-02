package com.subscription.application.invoice;

import com.subscription.domain.model.invoice.Invoice;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record IssueInvoiceResponse(
        UUID id,
        UUID subscriptionId,
        BigDecimal amount,
        LocalDate dueDate,
        String status,
        UUID planId,
        UUID priceVersionId
) {
    public static IssueInvoiceResponse from(Invoice invoice) {
        return new IssueInvoiceResponse(
                invoice.id().value(),
                invoice.getSubscriptionId().value(),
                invoice.getAmount().asBigDecimal(),
                invoice.getDueDate(),
                invoice.getStatus().name(),
                invoice.getPlanId().value(),
                invoice.getPriceVersionId().value()
        );
    }
}


