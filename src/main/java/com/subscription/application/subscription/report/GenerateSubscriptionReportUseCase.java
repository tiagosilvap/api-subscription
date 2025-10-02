package com.subscription.application.subscription.report;

import com.subscription.domain.repository.ReportRepository;
import com.subscription.domain.vo.SubscriptionReportRow;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class GenerateSubscriptionReportUseCase {
    private final ReportRepository reportRepo;

    public GenerateSubscriptionReportUseCase(ReportRepository repo) {
        this.reportRepo = repo;
    }

    public List<GenerateSubscriptionReportResponse> handle(
            Optional<String> customerId,
            Optional<String> status,
            Optional<LocalDate> from,
            Optional<LocalDate> to) {

        List<SubscriptionReportRow> rows = reportRepo.search(customerId, status, from, to);

        return rows.stream()
                .map(row -> new GenerateSubscriptionReportResponse(
                        row.subscriptionId(),
                        row.customerName(),
                        row.planName(),
                        row.status(),
                        row.amount(),
                        row.invoicesAmount()))
                .toList();
    }
}

