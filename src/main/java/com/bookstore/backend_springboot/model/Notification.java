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
@Table(name = "notifications")
public class Notification extends BaseEntity {
    String title;
    String message;

    @Column(columnDefinition = "boolean default false")
    boolean isRead;
    String type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
