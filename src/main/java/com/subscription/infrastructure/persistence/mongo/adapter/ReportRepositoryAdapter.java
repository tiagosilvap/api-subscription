package com.subscription.infrastructure.persistence.mongo.adapter;

import com.subscription.domain.vo.SubscriptionReportRow;
import com.subscription.domain.repository.ReportRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;

@Repository
public class ReportRepositoryAdapter implements ReportRepository {

    private final MongoTemplate mongoTemplate;

    public ReportRepositoryAdapter(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<SubscriptionReportRow> search(
            Optional<String> customerId,
            Optional<String> status,
            Optional<LocalDate> from,
            Optional<LocalDate> to) {

        Criteria criteria = new Criteria();

        customerId.ifPresent(id -> criteria.and("customerId").is(id));
        status.ifPresent(subscriptionStatus -> criteria.and("status").is(subscriptionStatus));

        Criteria dateCriteria = Criteria.where("startedAt");
        if (from.isPresent()) {
            dateCriteria = dateCriteria.gte(from.get().atStartOfDay());
        }
        if (to.isPresent()) {
            dateCriteria = dateCriteria.lte(to.get().atStartOfDay());
        }
        if (from.isPresent() || to.isPresent()) {
            criteria.andOperator(dateCriteria);
        }

        Aggregation agg = Aggregation.newAggregation(
                Aggregation.match(criteria),
                Aggregation.lookup("customers", "customerId", "_id", "customer"),
                Aggregation.lookup("plans", "pricingTerms.planId", "_id", "plan"),
                Aggregation.lookup("invoices", "_id", "subscriptionId", "invoice"),
                Aggregation.project("status")
                        .and("_id").as("subscriptionId")
                        .and("customer.name").as("customerName")
                        .and("plan.name").as("planName")
                        .and("invoice.amount").as("invoicesAmount")
                        .and("pricingTerms.amount").as("amount")
        );

        return mongoTemplate
                .aggregate(agg, "subscriptions", SubscriptionReportRow.class)
                .getMappedResults();
    }
}


