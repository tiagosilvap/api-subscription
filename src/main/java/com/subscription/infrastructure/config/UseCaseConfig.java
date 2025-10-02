//package com.subscription.infrastructure.config;
//
//import com.subscription.application.usecase.CancelSubscriptionUseCase;
//import com.subscription.application.usecase.CreateSubscriptionUseCase;
//import com.subscription.application.usecase.GenerateSubscriptionReportUseCase;
//import com.subscription.domain.repository.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///*Use cases anotados com @Service nao precisam desta config manual*/
//
//@Configuration
//public class UseCaseConfig {
//    @Bean
//    CreateSubscriptionUseCase createSubscription(
//            SubscriptionRepository subscriptionRepository,
//            InvoiceRepository invoiceRepository,
//            CustomerRepository customerRepository,
//            PlanRepository planRepository) {
//        return new CreateSubscriptionUseCase(
//                subscriptionRepository,
//                invoiceRepository,
//                customerRepository,
//                planRepository);
//    }
//
//    @Bean
//    CancelSubscriptionUseCase cancelSubscription(SubscriptionRepository s) {
//        return new CancelSubscriptionUseCase(s);
//    }
//
//    @Bean
//    GenerateSubscriptionReportUseCase reportUseCase(ReportRepository r) {
//        return new GenerateSubscriptionReportUseCase(r);
//    }
//}
