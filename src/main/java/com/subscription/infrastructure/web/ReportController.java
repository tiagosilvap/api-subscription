package com.subscription.infrastructure.web;

import com.subscription.application.dto.ReportRowView;
import com.subscription.application.usecase.GenerateSubscriptionReportUseCase;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final GenerateSubscriptionReportUseCase useCase;

    public ReportController(GenerateSubscriptionReportUseCase generateSubscriptionReportUseCase) {
        this.useCase = generateSubscriptionReportUseCase;
    }

    // 3) Relatório com filtros opcionais (multi-tabela -> VO)
    @GetMapping("/subscriptions")
    public List<ReportRowView> report(@RequestParam Optional<UUID> customerId,
                                      @RequestParam Optional<String> status,
                                      @RequestParam Optional<LocalDate> from,
                                      @RequestParam Optional<LocalDate> to) {
        return useCase.handle(customerId, status, from, to);
    }
}

