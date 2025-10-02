package com.subscription.domain.common;

import java.time.LocalDate;

public record Period(LocalDate start, LocalDate end) {
    public Period {
        if (start == null || end == null || end.isBefore(start))
            throw new IllegalArgumentException("Invalid period");
    }

    public boolean contains(LocalDate date) {
        return !date.isBefore(start) && !date.isAfter(end);
    }
}
