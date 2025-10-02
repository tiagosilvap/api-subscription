package com.subscription.infrastructure.config;

import com.subscription.infrastructure.persistence.mongo.document.CustomerDocument;
import com.subscription.infrastructure.persistence.mongo.document.plan.PlanDocument;
import com.subscription.infrastructure.persistence.mongo.document.plan.PriceVersionDocument;
import com.subscription.infrastructure.persistence.mongo.repository.CustomerMongoRepo;
import com.subscription.infrastructure.persistence.mongo.repository.PlanMongoRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;

@Configuration
public class DatabaseSeeder {

    @Bean
    CommandLineRunner seedDatabase(PlanMongoRepo planRepo, CustomerMongoRepo customerRepo) {
        return args -> {

            if (planRepo.count() == 0 && customerRepo.count() == 0) {

                var priceVersion = new PriceVersionDocument();
                priceVersion.setId(UUID.randomUUID().toString());
                priceVersion.setAmount(new BigDecimal("49.90"));
                priceVersion.setCreatedAt(LocalDate.now());

                var basicPlan = new PlanDocument();
                basicPlan.setId("65c1920f-c483-43c4-aa3c-cf44e77d014f");
                basicPlan.setName("Plano Básico");
                basicPlan.setVersions(Arrays.asList(priceVersion));
                basicPlan.setCurrentVersionId(priceVersion.getId());
                planRepo.save(basicPlan);

                priceVersion = new PriceVersionDocument();
                priceVersion.setId(UUID.randomUUID().toString());
                priceVersion.setAmount(new BigDecimal("200.00"));
                priceVersion.setCreatedAt(LocalDate.now());

                var expensivePlan = new PlanDocument();
                expensivePlan.setId("8761920f-c483-43c4-aa3c-cf44e77d765d");
                expensivePlan.setName("Plano Caro");
                expensivePlan.setVersions(Arrays.asList(priceVersion));
                expensivePlan.setCurrentVersionId(priceVersion.getId());
                planRepo.save(expensivePlan);

                var customer = new CustomerDocument();
                customer.setId("d4e16dca-adc6-4f33-be42-c4a2d7bc4830");
                customer.setName("João da Silva");
                customer.setEmail("joao@example.com");
                customerRepo.save(customer);

                System.out.println("✅ Banco populado com 2 planos e 1 cliente:");
                System.out.println("📦 Plano básico: " + basicPlan.getId());
                System.out.println("📦 Plano caro: " + expensivePlan.getId());
                System.out.println("👤 Cliente: " + customer.getId());
            }
        };
    }
}
