package com.subscription.infrastructure.persistence.adapter;

import com.subscription.domain.dto.SubscriptionReportRow;
import com.subscription.domain.model.subscription.SubscriptionStatus;
import com.subscription.domain.repository.ReportRepository;
import jakarta.persistence.*;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.*;

@Repository
public class ReportRepositoryAdapter implements ReportRepository {
    private final EntityManager em;

    public ReportRepositoryAdapter(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<SubscriptionReportRow> search(
            Optional<UUID> customerId,
            Optional<String> status,
            Optional<LocalDate> from,
            Optional<LocalDate> to) {

        StringBuilder jpql = new StringBuilder()
                .append("select s.id, c.name, p.name, s.status, coalesce(i.amount, 0) ")
                .append("from SubscriptionEntity s ")
                .append("left join s.customer c ")
                .append("left join s.plan p ")
                .append("left join InvoiceEntity i on i.subscriptionId = s.id ")
                .append("where 1=1 ");

        if (customerId.isPresent()) jpql.append("and s.customerId = :customerId ");
        if (status.isPresent()) jpql.append("and s.status = :status ");
        if (from.isPresent()) jpql.append("and i.dueDate >= :from ");
        if (to.isPresent()) jpql.append("and i.dueDate <= :to ");
        jpql.append("group by s.id, c.name, p.name, s.status, i.amount");

        Query q = em.createQuery(jpql.toString());
        customerId.ifPresent(v -> q.setParameter("customerId", v));
        status.ifPresent(v -> q.setParameter("status", SubscriptionStatus.valueOf(v)));
        from.ifPresent(v -> q.setParameter("from", v));
        to.ifPresent(v -> q.setParameter("to", v));

        @SuppressWarnings("unchecked")
        List<Object[]> rows = q.getResultList();

        return rows.stream().map(r -> new SubscriptionReportRow(
                (UUID) r[0],
                (String) r[1],
                (String) r[2],
                (SubscriptionStatus) r[3],
                (BigDecimal) r[4]
        )).collect(Collectors.toList());
    }
}

