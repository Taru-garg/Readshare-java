package com.taru.readshare.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Entity
public class Team {

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,
            mappedBy = "teams", orphanRemoval = true
    )
    @JsonIgnore()
    private final Set<TeamUsers> users = new HashSet<>();
    @Id
    @GeneratedValue
    private UUID id;

    @NotNull
    @Column(nullable = false)
    private String TeamName;

    @NotNull
    @Column(nullable = false)
    private String TeamDescription;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private User admin;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    @JsonManagedReference
    private Set<Link> links = new HashSet<>();

    public Team() {
        super();
    }

    public Team(UUID id, String teamName, String teamDescription, User admin, Set<Link> links) {
        this.id = id;
        TeamName = teamName;
        TeamDescription = teamDescription;
        this.admin = admin;
        this.links = links;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTeamName() {
        return TeamName;
    }

    public void setTeamName(String teamName) {
        TeamName = teamName;
    }

    public String getTeamDescription() {
        return TeamDescription;
    }

    public void setTeamDescription(String teamDescription) {
        TeamDescription = teamDescription;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public Set<TeamUsers> getUsers() {
        return users;
    }

    public Set<Link> getLinks() {
        return links;
    }

    public void setLinks(Set<Link> links) {
        this.links = links;
    }
}
