package com.subscription.domain.repository;

import com.subscription.domain.dto.SubscriptionReportRow;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReportRepository {
    List<SubscriptionReportRow> search(Optional<UUID> customerId, Optional<String> status, Optional<LocalDate> from, Optional<LocalDate> to);
}
