package com.subscription.infrastructure.persistence.mongo.adapter;

import com.subscription.domain.model.invoice.Invoice;
import com.subscription.domain.repository.InvoiceRepository;
import com.subscription.infrastructure.persistence.mongo.mapper.InvoiceMapper;
import com.subscription.infrastructure.persistence.mongo.repository.InvoiceMongoRepo;
import org.springframework.stereotype.Repository;

@Repository
public class InvoiceRepositoryAdapter implements InvoiceRepository {

    private final InvoiceMongoRepo invoiceMongoRepo;

    public InvoiceRepositoryAdapter(InvoiceMongoRepo invoiceMongoRepo) {
        this.invoiceMongoRepo = invoiceMongoRepo;
    }

    @Override
    public void save(Invoice invoice) {
        invoiceMongoRepo.save(InvoiceMapper.toDoc(invoice));
    }
}


