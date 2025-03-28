package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column(name = "user_name")
    private String userName;
    private String password;
    private String email;
    private String phone;
    private boolean active;

    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<Role> roles;
}
