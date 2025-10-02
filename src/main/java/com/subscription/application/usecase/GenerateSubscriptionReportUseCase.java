package com.subscription.application.usecase;

import com.subscription.application.dto.ReportRowView;
import com.subscription.domain.repository.ReportRepository;
import com.subscription.domain.dto.SubscriptionReportRow;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class GenerateSubscriptionReportUseCase {
    private final ReportRepository reportRepo;

    public GenerateSubscriptionReportUseCase(ReportRepository repo) {
        this.reportRepo = repo;
    }

    public List<ReportRowView> handle(
            Optional<UUID> customerId,
            Optional<String> status,
            Optional<LocalDate> from,
            Optional<LocalDate> to) {
        List<SubscriptionReportRow> rows = reportRepo.search(customerId, status, from, to);
        return rows.stream()
                .map(r -> new ReportRowView(
                        r.subscriptionId(),
                        r.customerName(),
                        r.planName(),
                        r.status(),
                        r.currentInvoiceAmount()))
                .toList();
    }
}

