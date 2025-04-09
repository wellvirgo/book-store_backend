package com.bookstore.backend_springboot.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "carts")
public class Cart extends BaseEntity {
    @Column(nullable = false)
    long totalAmount;

    @Column(columnDefinition = "boolean")
    boolean isSelectedAll;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;
}
