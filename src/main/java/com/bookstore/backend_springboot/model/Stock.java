package com.bookstore.backend_springboot.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "stocks")
public class Stock extends BaseEntity {
    long quantity_in;
    LocalDateTime transaction_date;

    @Column(columnDefinition = "MEDIUMTEXT")
    String note;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;
}
