package com.bookstore.backend_springboot.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "vouchers")
public class Voucher extends BaseEntity {
    @Column(nullable = false, unique = true)
    String code;

    @Column(columnDefinition = "TEXT")
    String description;
    String discount_type;
    int discount_value;
    long minOrderValue;
    LocalDateTime startDate;
    LocalDateTime endDate;
    int usageLimit;
    int usagePerUserLimit;

    @Column(columnDefinition = "boolean default true")
    boolean isActive;

    @ManyToMany
    @JoinTable(
            name = "user_voucher",
            joinColumns = @JoinColumn(name = "voucher_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    Set<User> users = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "product_voucher",
            joinColumns = @JoinColumn(name = "voucher_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    Set<Product> products = new HashSet<>();
}
