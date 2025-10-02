package com.subscription.infrastructure.persistence.mongo.document.plan;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "plans")
@Getter
@Setter
public class PlanDocument {
    @Id
    private String id;
    private String name;
    private List<PriceVersionDocument> versions;
    private String currentVersionId;
}
