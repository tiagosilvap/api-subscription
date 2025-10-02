package com.subscription.infrastructure.web;

import com.subscription.application.plan.usecase.ChangeSubscriptionPlanUseCase;
import com.subscription.application.subscription.create.CreateSubscriptionResponse;
import com.subscription.application.subscription.cancel.CancelSubscriptionUseCase;
import com.subscription.application.subscription.create.CreateSubscriptionUseCase;
import com.subscription.application.subscription.create.CreateSubscriptionCommand;
import com.subscription.domain.model.plan.PlanId;
import com.subscription.domain.model.subscription.SubscriptionId;
import com.subscription.infrastructure.web.request.ChangePlanRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    private final CreateSubscriptionUseCase createSubscriptionUseCase;
    private final CancelSubscriptionUseCase cancelSubscriptionUseCase;
    private final ChangeSubscriptionPlanUseCase changeSubscriptionPlanUseCase;

    public SubscriptionController(
            CreateSubscriptionUseCase createSubscriptionUseCase,
            CancelSubscriptionUseCase cancelSubscriptionUseCase,
            ChangeSubscriptionPlanUseCase changeSubscriptionPlanUseCase) {
        this.createSubscriptionUseCase = createSubscriptionUseCase;
        this.cancelSubscriptionUseCase = cancelSubscriptionUseCase;
        this.changeSubscriptionPlanUseCase = changeSubscriptionPlanUseCase;
    }

    @PostMapping
    public ResponseEntity<CreateSubscriptionResponse> create(
            @RequestBody CreateSubscriptionCommand createSubscriptionCommand) {
        return ResponseEntity.ok(createSubscriptionUseCase.handle(createSubscriptionCommand));
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<Void> cancel(@PathVariable UUID id) {
        cancelSubscriptionUseCase.handle(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/plan")
    public ResponseEntity<Void> changePlan(@PathVariable UUID id, @RequestBody ChangePlanRequest req) {
        changeSubscriptionPlanUseCase.handle(new SubscriptionId(id), new PlanId(req.newPlanId()));
        return ResponseEntity.noContent().build();
    }
}

