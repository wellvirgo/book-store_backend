package com.bookstore.backend_springboot.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "products")
public class Product extends BaseEntity {
    String name;
    String author;
    String supplier;
    String publisher;
    String book_layout;
    long price;
    String productCode;
    int publishYear;
    String language;
    int weight;
    String size;
    int quantityPage;
    int quantityAvailable;
    String description;

    @Column(columnDefinition = "boolean default false")
    boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    Genre genre;

    @ManyToMany(mappedBy = "products")
    Set<Voucher> vouchers = new HashSet<>();
}
