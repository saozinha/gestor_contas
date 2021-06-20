package com.lourenco.gestor_contas.dal;

import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

public class BaseEntity  {

    @Field(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Field(name = "updated_at")
    private LocalDateTime updatedAt = null;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
