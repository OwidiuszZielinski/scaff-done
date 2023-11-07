package com.dev.scaffdone.core.user;

import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    private String email;
    private String login;
    private char[] password;

    private Set<Role> roles;

}
