package com.subscription.infrastructure.web;

import com.subscription.application.subscription.report.GenerateSubscriptionReportResponse;
import com.subscription.application.subscription.report.GenerateSubscriptionReportUseCase;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final GenerateSubscriptionReportUseCase reportUseCase;

    public ReportController(GenerateSubscriptionReportUseCase generateSubscriptionReportUseCase) {
        this.reportUseCase = generateSubscriptionReportUseCase;
    }

    @GetMapping("/subscriptions")
    public List<GenerateSubscriptionReportResponse> report(@RequestParam Optional<String> customerId,
                                                           @RequestParam Optional<String> status,
                                                           @RequestParam Optional<LocalDate> from,
                                                           @RequestParam Optional<LocalDate> to) {
        return reportUseCase.handle(customerId, status, from, to);
    }
}

