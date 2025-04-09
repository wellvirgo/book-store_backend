package com.bookstore.backend_springboot.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "genres")
public class Genre extends BaseEntity {
    String name;

    @Column(columnDefinition = "TEXT")
    String description;

    @Column(columnDefinition = "boolean default false", nullable = false)
    boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    Category category;
}
