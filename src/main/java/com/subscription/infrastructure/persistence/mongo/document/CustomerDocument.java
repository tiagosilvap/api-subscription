package com.subscription.infrastructure.persistence.mongo.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "customers")
@Getter
@Setter
public class CustomerDocument {
    @Id
    public String id;
    public String name;
    public String email;
}
