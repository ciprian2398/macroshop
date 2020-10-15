package com.technetwork.macroshop.model;

import com.technetwork.macroshop.model.enums.CredentialsRole;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login", nullable = false)
    private String login;

    @ToString.Exclude
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "wallet", nullable = false)
    private Double wallet;

    @Column(name = "credentials_role", nullable = false)
    private CredentialsRole credentialsRole;

    @ToString.Exclude
    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<Product> products;

}
