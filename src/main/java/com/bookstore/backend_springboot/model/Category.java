package com.bookstore.backend_springboot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "categories")
public class Category extends BaseEntity{
    String name;

    @Column(columnDefinition = "TEXT")
    String description;

    @Column(columnDefinition = "boolean default false", nullable = false)
    boolean isDeleted;
}
