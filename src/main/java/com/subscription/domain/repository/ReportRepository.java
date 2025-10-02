package com.subscription.domain.repository;

import com.subscription.domain.vo.SubscriptionReportRow;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReportRepository {
    List<SubscriptionReportRow> search(Optional<String> customerId, Optional<String> status, Optional<LocalDate> from, Optional<LocalDate> to);
}
