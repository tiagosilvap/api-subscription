package com.subscription.application.dto;

import java.util.UUID; import java.time.LocalDate; import java.math.BigDecimal;

public record CreateSubscriptionCommand(
        UUID customerId,
        UUID planId,
        LocalDate startDate,
        BigDecimal firstInvoiceAmount,
        LocalDate firstInvoiceDueDate
) {}

