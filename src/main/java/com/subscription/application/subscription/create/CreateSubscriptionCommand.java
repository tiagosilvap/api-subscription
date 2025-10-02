package com.subscription.application.subscription.create;

import java.util.UUID; import java.time.LocalDate; import java.math.BigDecimal;

public record CreateSubscriptionCommand(
        UUID customerId,
        UUID planId,
        LocalDate firstInvoiceDueDate
) {}

