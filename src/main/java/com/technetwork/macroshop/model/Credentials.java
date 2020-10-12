package com.technetwork.macroshop.model;

import com.technetwork.macroshop.model.enums.CredentialsRole;

import javax.persistence.*;
import java.util.Objects;

//@Data todo
@Entity
@Table(name = "CREDENTIALS")
public class Credentials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "credentials_role", nullable = false)
    private CredentialsRole credentialsRole;

    public Credentials() {
    }

    public Credentials(String username, String password, CredentialsRole credentialsRole) {
        this.username = username;
        this.password = password;
        this.credentialsRole = credentialsRole;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CredentialsRole getCredentialsRole() {
        return credentialsRole;
    }

    public void setCredentialsRole(CredentialsRole credentialsRole) {
        this.credentialsRole = credentialsRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Credentials)) return false;
        Credentials that = (Credentials) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                credentialsRole == that.credentialsRole;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, credentialsRole);
    }

    @Override
    public String toString() {
        return "Credentials{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", credentialsRole=" + credentialsRole +
                '}';
    }
}
