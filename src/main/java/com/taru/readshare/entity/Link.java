package com.taru.readshare.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.springframework.stereotype.Indexed;

import javax.validation.constraints.NotNull;
import java.net.URL;
import java.util.List;
import java.util.UUID;

@Entity
@Indexed
public class Link {

    @Id
    @GeneratedValue
    private UUID id;

    @NotNull
    @Column(nullable = false)
    private URL url;

    @NotNull
    @Column(nullable = false)
    private String title;

    @NotNull
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = true)
    private List<String> tags;

    @JoinColumn(name = "team_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Team team;

    public Link() {
        super();
    }

    public Link(UUID id, URL url, String title, String description, List<String> tags) {
        this.id = id;
        this.url = url;
        this.title = title;
        this.description = description;
        this.tags = tags;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Link{" +
                "id=" + id +
                ", url=" + url +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", tags=" + tags +
                ", team=" + team +
                '}';
    }
}
