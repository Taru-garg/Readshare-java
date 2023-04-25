package com.taru.readshare.repository;

import com.taru.readshare.entity.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LinkRepository extends JpaRepository<Link, UUID> {
    @Query(value = "SELECT * from link where title % :query OR description % :query OR url % :query AND team_id = :team_id",
            nativeQuery = true
    )
    List<Link> searchWithTeamId(String query, UUID team_id);

    @Query(value = "SELECT * from link where lower(title) % lower(:query) OR " +
            "lower(description) % lower(:query) " +
            "OR lower(url) % lower(:query)",
            nativeQuery = true
    )
    List<Link> searchAll(String query);
}
