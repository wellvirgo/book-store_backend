package com.bookstore.backend_springboot.model;

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
@Table(name = "banners")
public class Banner extends BaseEntity {
    String imageUrl;
    String linkTo;
}
