package com.bookstore.backend_springboot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    String email;
    String password;
    String firstName;
    String lastName;
    String gender;
    String telephone;
    String userType;
    String profileImage;

    @Column(columnDefinition = "boolean default false")
    boolean isDeleted;

    @ManyToMany(mappedBy = "users")
    Set<Voucher> vouchers = new HashSet<>();
}
