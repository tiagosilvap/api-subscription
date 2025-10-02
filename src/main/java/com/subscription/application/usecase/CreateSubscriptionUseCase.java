package com.subscription.application.usecase;

import com.subscription.application.dto.CreateSubscriptionCommand;
import com.subscription.application.dto.SubscriptionView;
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
    public SubscriptionView handle(CreateSubscriptionCommand cmd) {
//         validações ricas via domínio (existência, regras, etc.)
        customerRepo.findById(new CustomerId(cmd.customerId()))
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        planRepo.findById(new PlanId(cmd.planId()))
                .orElseThrow(() -> new IllegalArgumentException("Plan not found"));

        Subscription sub = Subscription.start(new CustomerId(cmd.customerId()), new PlanId(cmd.planId()), cmd.startDate());
        subscriptionRepo.save(sub);

        Invoice first = sub.issueFirstInvoice(Money.of(cmd.firstInvoiceAmount()), cmd.firstInvoiceDueDate());
        invoiceRepo.save(first);

        return new SubscriptionView(sub.id().value(), cmd.customerId(), cmd.planId(), sub.status(), cmd.startDate());
    }
}

