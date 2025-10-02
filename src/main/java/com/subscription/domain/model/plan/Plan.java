package com.subscription.domain.model.plan;

import com.subscription.domain.common.Money;
import com.subscription.domain.model.priceversion.PriceVersion;
import com.subscription.domain.model.priceversion.PriceVersionId;

import java.time.LocalDate;
import java.util.List;

public final class Plan {

    private final PlanId id;
    private String name;
    private final List<PriceVersion> versions;
    private PriceVersionId currentVersionId;

    public Plan(PlanId id, String name, List<PriceVersion> versions, PriceVersionId currentVersionId) {
        this.id = id;
        this.name = name;
        this.versions = versions;
        this.currentVersionId = currentVersionId;
    }

    public PriceVersion currentVersion() {
        return versions.stream()
                .filter(version ->
                        version.getId().equals(currentVersionId))
                .findFirst()
                .orElseThrow();
    }

    public PriceVersion addNewPrice(Money newPrice) {
        PriceVersion priceVersion = new PriceVersion(PriceVersionId.newId(), newPrice, LocalDate.now());
        versions.add(priceVersion);
        currentVersionId = priceVersion.getId();
        return priceVersion;
    }

    public PlanId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<PriceVersion> getVersions() {
        return versions;
    }
}