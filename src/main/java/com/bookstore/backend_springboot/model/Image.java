package com.bookstore.backend_springboot.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "images")
public class Image extends BaseEntity {
    String url;

    @Column(columnDefinition = "boolean")
    boolean isPrimary;
    int imgOrder;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;
}
