package com.technetwork.macroshop.model;

import com.technetwork.macroshop.model.enums.CredentialsRole;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "wallet", nullable = false)
    private Long wallet;

    @Column(name = "credentials_role", nullable = false)
    private CredentialsRole credentialsRole;

}
