package com.subscription.domain.repository;

import com.subscription.domain.model.invoice.Invoice;

public interface InvoiceRepository {
    void save(Invoice i);
}

