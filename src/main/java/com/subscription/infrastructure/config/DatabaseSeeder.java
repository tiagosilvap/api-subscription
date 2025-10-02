package com.subscription.infrastructure.config;

import com.subscription.infrastructure.persistence.jpa.repository.PlanJpaRepo;
import com.subscription.infrastructure.persistence.jpa.repository.CustomerJpaRepo;
import com.subscription.infrastructure.persistence.jpa.entity.PlanEntity;
import com.subscription.infrastructure.persistence.jpa.entity.CustomerEntity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.UUID;

@Configuration
public class DatabaseSeeder {

    @Bean
    CommandLineRunner seedDatabase(PlanJpaRepo planRepo, CustomerJpaRepo customerRepo) {
        return args -> {

            if (planRepo.count() == 0 && customerRepo.count() == 0) {
                // Cria um plano
                var basicPlan = new PlanEntity();
                basicPlan.setId(UUID.fromString("65c1920f-c483-43c4-aa3c-cf44e77d014f"));
                basicPlan.setName("Plano Básico");
                basicPlan.setPrice(BigDecimal.valueOf(49,90));
                planRepo.save(basicPlan);

                // Cria um cliente
                var customer = new CustomerEntity();
                customer.setId(UUID.fromString("d4e16dca-adc6-4f33-be42-c4a2d7bc4830"));
                customer.setName("João da Silva");
                customer.setEmail("joao@example.com");
                customerRepo.save(customer);

                System.out.println("✅ Banco populado com 1 plano e 1 cliente:");
                System.out.println("📦 Plano: " + basicPlan.getId());
                System.out.println("👤 Cliente: " + customer.getId());
            }
        };
    }
}
