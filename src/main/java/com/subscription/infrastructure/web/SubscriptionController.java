package com.subscription.infrastructure.web;

import com.subscription.application.dto.*;
import com.subscription.application.usecase.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {
    private final CreateSubscriptionUseCase createUC;
    private final CancelSubscriptionUseCase cancelUC;

    public SubscriptionController(
            CreateSubscriptionUseCase createSubscriptionUseCase,
            CancelSubscriptionUseCase cancelSubscriptionUseCase) {
        this.createUC = createSubscriptionUseCase;
        this.cancelUC = cancelSubscriptionUseCase;
    }

    // 1) Cria assinatura + fatura inicial (grava em >1 tabela)
    @PostMapping
    public ResponseEntity<SubscriptionView> create(
            @RequestBody CreateSubscriptionCommand createSubscriptionCommand) {
        return ResponseEntity.ok(createUC.handle(createSubscriptionCommand));
    }

    // 2) Cancela assinatura (estado rico)
    @PatchMapping("/{id}/cancel")
    public ResponseEntity<Void> cancel(@PathVariable UUID id) {
        cancelUC.handle(id);
        return ResponseEntity.noContent().build();
    }
}

