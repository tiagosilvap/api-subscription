package com.subscription.application.invoice;

import com.subscription.domain.model.subscription.SubscriptionId;
import com.subscription.domain.repository.InvoiceRepository;
import com.subscription.domain.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class IssueInvoiceUseCase {

    private final SubscriptionRepository subscriptionRepository;
    private final InvoiceRepository invoiceRepository;

    public IssueInvoiceUseCase(SubscriptionRepository subscriptionRepository, InvoiceRepository invoiceRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.invoiceRepository = invoiceRepository;
    }

    public IssueInvoiceResponse handle(UUID subId, LocalDate due) {
        var subscription = subscriptionRepository.findById(new SubscriptionId(subId)).orElseThrow();
        var invoice = subscription.issueInvoice(due);
        invoiceRepository.save(invoice);
        return IssueInvoiceResponse.from(invoice);
    }
}