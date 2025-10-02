package com.subscription.application.subscription.create;

import com.subscription.domain.common.Money;
import com.subscription.domain.model.invoice.Invoice;
import com.subscription.domain.model.subscription.Subscription;
import com.subscription.domain.repository.InvoiceRepository;
import com.subscription.domain.repository.PlanRepository;
import com.subscription.domain.repository.SubscriptionRepository;
import com.subscription.domain.repository.CustomerRepository;
import com.subscription.domain.model.customer.CustomerId;
import com.subscription.domain.model.plan.PlanId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateSubscriptionUseCase {
    private final SubscriptionRepository subscriptionRepo;
    private final InvoiceRepository invoiceRepo;
    private final CustomerRepository customerRepo;
    private final PlanRepository planRepo;

    public CreateSubscriptionUseCase(
            SubscriptionRepository subscriptionRepository,
            InvoiceRepository invoiceRepository,
            CustomerRepository customerRepository,
            PlanRepository planRepository) {
        this.subscriptionRepo = subscriptionRepository;
        this.invoiceRepo = invoiceRepository;
        this.customerRepo = customerRepository;
        this.planRepo = planRepository;
    }

    @Transactional
    public CreateSubscriptionResponse handle(CreateSubscriptionCommand cmd) {

        var customer = customerRepo.findById(new CustomerId(cmd.customerId()))
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        var plan = planRepo.findById(new PlanId(cmd.planId()))
                .orElseThrow(() -> new IllegalArgumentException("Plan not found"));

        Subscription subscription = Subscription.start(customer.id(), plan);

        subscriptionRepo.save(subscription);

        Invoice first = subscription.issueFirstInvoice(cmd.firstInvoiceDueDate());

        invoiceRepo.save(first);

        return new CreateSubscriptionResponse(
                subscription.getId().value(),
                cmd.customerId(),
                cmd.planId(),
                subscription.getStatus(),
                subscription.getStartedAt());
    }
}

