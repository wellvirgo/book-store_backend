package com.bookstore.backend_springboot.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    String status;
    LocalDateTime orderDate;
    LocalDateTime deliveryDate;
    String paymentMethod;
    String paymentStatus;

    @Column(nullable = false)
    long totalAmount;
    long shippingFee;

    @Column(columnDefinition = "MEDIUMTEXT")
    String note;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
