//package com.subscription.infrastructure.config;
//
//import com.subscription.domain.repository.*;
//import com.subscription.infrastructure.persistence.adapter.*;
//import com.subscription.infrastructure.persistence.jpa.repository.CustomerJpaRepo;
//import com.subscription.infrastructure.persistence.jpa.repository.InvoiceJpaRepo;
//import com.subscription.infrastructure.persistence.jpa.repository.PlanJpaRepo;
//import com.subscription.infrastructure.persistence.jpa.repository.SubscriptionJpaRepo;
//import jakarta.persistence.EntityManager;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///*Repositories anotados com @Repository nao precisam desta config manual*/
//
//@Configuration
//public class RepositoryConfig {
//    @Bean
//    PlanRepository planRepository(PlanJpaRepo jpa) {
//        return new PlanRepositoryAdapter(jpa);
//    }
//
//    @Bean
//    SubscriptionRepository subscriptionRepository(
//            SubscriptionJpaRepo subscriptionJpaRepo,
//            PlanJpaRepo planJpaRepo,
//            CustomerJpaRepo customerJpaRepo) {
//        return new SubscriptionRepositoryAdapter(subscriptionJpaRepo, planJpaRepo, customerJpaRepo);
//    }
//
//    @Bean
//    InvoiceRepository invoiceRepository(InvoiceJpaRepo jpa) {
//        return new InvoiceRepositoryAdapter(jpa);
//    }
//
//    @Bean
//    CustomerRepository customerRepository(CustomerJpaRepo jpa) {
//        return new CustomerRepositoryAdapter(jpa);
//    }
//
//    @Bean
//    ReportRepository reportRepository(EntityManager em) {
//        return new ReportRepositoryAdapter(em);
//    }
//}
