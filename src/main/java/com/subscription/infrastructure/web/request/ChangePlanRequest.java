package com.subscription.infrastructure.web.request;

import java.util.UUID;

public record ChangePlanRequest(UUID newPlanId) {
}
