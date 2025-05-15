package com.bookstore.backend_springboot.dto;

import com.bookstore.backend_springboot.model.Genre;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDetailDTO {
    Long id;
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
    String genreName;
    String categoryName;
}