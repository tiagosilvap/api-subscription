package com.subscription.infrastructure.persistence.adapter;

import com.subscription.domain.model.invoice.Invoice;
import com.subscription.domain.repository.InvoiceRepository;
import com.subscription.infrastructure.persistence.jpa.repository.InvoiceJpaRepo;
import org.springframework.stereotype.Repository;

import java.util.Objects;

import static com.subscription.infrastructure.persistence.mapper.InvoiceMapper.*;

@Repository
public class InvoiceRepositoryAdapter implements InvoiceRepository {
    private final InvoiceJpaRepo jpa;

    public InvoiceRepositoryAdapter(InvoiceJpaRepo jpa) {
        this.jpa = jpa;
    }

    @Override
    public void save(Invoice invoice) {
        jpa.save(toEntity(invoice));
    }
}

