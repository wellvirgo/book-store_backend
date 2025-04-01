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
@Table(name = "information")
public class Information extends BaseEntity{
    String type;
    String title;
    String content;

    @Column(columnDefinition = "boolean")
    boolean is_active;
}
