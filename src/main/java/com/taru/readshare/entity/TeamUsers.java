package com.taru.readshare.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "team_users")
public class TeamUsers {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(targetEntity = Team.class)
    @JoinColumn(name = "team_id")
    @JsonIgnoreProperties("team_users")
    private Team teams;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("team_users")
    private User users;

    private Role role;

    public TeamUsers(UUID id, Team teams, User users, Role role) {
        this.id = id;
        this.teams = teams;
        this.users = users;
        this.role = role;
    }

    public TeamUsers() {
        super();
    }

    @Override
    public String toString() {
        return "TeamUsers{" +
                "id=" + id +
                ", teams=" + teams +
                ", users=" + users +
                ", role=" + role +
                '}';
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Team getTeams() {
        return teams;
    }

    public void setTeams(Team teams) {
        this.teams = teams;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }
}
