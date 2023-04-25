package com.taru.readshare.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private UUID Id;

    @NotNull
    @Column(name = "username", unique = true, nullable = false)
    private String Username;

    @NotNull
    @Column(name = "password", nullable = false)
    private String Password;

    @NotNull
    @Email
    @Column(name = "email", unique = true, nullable = false)
    private String Email;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,
            mappedBy = "users", orphanRemoval = true
    )
    @JsonIgnoreProperties("users")
    private Set<TeamUsers> teams = new HashSet<>();

    public User(UUID id, String username, String password, String email, Set<TeamUsers> teams) {
        Id = id;
        Username = username;
        Password = password;
        Email = email;
        this.teams = teams;
    }

    public User() {
        super();
    }

    public Set<TeamUsers> getTeams() {
        return teams;
    }

    public void setTeams(Set<TeamUsers> teams) {
        this.teams = teams;
    }

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    @JsonIgnore
    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Password = encoder.encode(password);
    }

    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        Email = email;
    }
}
