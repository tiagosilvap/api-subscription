package com.subscription.infrastructure.web;

import com.subscription.application.plan.usecase.UpdatePlanPriceUseCase;
import com.subscription.domain.common.Money;
import com.subscription.domain.model.plan.PlanId;
import com.subscription.infrastructure.web.request.UpdatePriceRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/plans")
public class PlanController {

    private final UpdatePlanPriceUseCase updatePlanPriceUseCase;

    public PlanController(UpdatePlanPriceUseCase updatePlanPriceUseCase) {
        this.updatePlanPriceUseCase = updatePlanPriceUseCase;
    }

    @PatchMapping("/{planId}/price")
    public ResponseEntity<Void> updatePrice(@PathVariable UUID planId, @RequestBody UpdatePriceRequest req) {
        updatePlanPriceUseCase.handle(new PlanId(planId), Money.of(req.newPrice()), req.propagate());
        return ResponseEntity.accepted().build();
    }
}