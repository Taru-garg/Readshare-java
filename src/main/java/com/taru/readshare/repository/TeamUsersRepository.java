package com.taru.readshare.repository;

import com.taru.readshare.entity.TeamUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface TeamUsersRepository extends JpaRepository<TeamUsers, UUID> {
    @Query(value = "SELECT EXISTS(SELECT 1 FROM team_users tu WHERE tu.team_id = :teamId AND tu.user_id = :userId)",
            nativeQuery = true
    )
    boolean existsByTeamIdAndUserId(UUID teamId, UUID userId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM team_users tu WHERE tu.team_id = :teamId AND tu.user_id = :userId"
            , nativeQuery = true
    )
    void deleteByTeamIdAndUserId(UUID teamId, UUID userId);
}
