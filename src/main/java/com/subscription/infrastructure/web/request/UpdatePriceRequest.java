package com.subscription.infrastructure.web.request;

import java.math.BigDecimal;

public record UpdatePriceRequest(BigDecimal newPrice, boolean propagate) {
}
