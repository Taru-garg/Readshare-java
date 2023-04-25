package com.taru.readshare.dto.team;

import com.taru.readshare.dto.link.LinkMapper;
import com.taru.readshare.dto.user.UserMapper;
import com.taru.readshare.entity.Team;
import com.taru.readshare.entity.TeamUsers;
import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;

@Component
public class TeamMapper {

    public TeamDTO toTeamDTO (@Nonnull Team team) {
        TeamDTO teamDTO = new TeamDTO();
        UserMapper userMapper = new UserMapper();
        LinkMapper linkMapper = new LinkMapper();
        teamDTO.setTeamName(team.getTeamName());
        teamDTO.setTeamDescription(team.getTeamDescription());
        teamDTO.setLinks(
                team.getLinks()
                        .stream()
                        .map(linkMapper::toLinkDTO)
                        .toList()
        );
        teamDTO.setUsers(
                team.getUsers()
                        .stream()
                        .map(TeamUsers::getUsers)
                        .map(userMapper::toUserNoTeamDTO)
                        .toList()
        );
        return teamDTO;

    }

    public Team toTeam(@Nonnull TeamCreationDTO teamCreationDTO) {
        Team team = new Team();
        team.setTeamName(teamCreationDTO.getTeamName());
        team.setTeamDescription(teamCreationDTO.getTeamDescription());
        return team;
    }

    public TeamNoUserDTO toTeamNoUserDTO(@Nonnull TeamUsers teamUsers) {
        TeamNoUserDTO teamNoUserDTO = new TeamNoUserDTO();
        UserMapper userMapper = new UserMapper();
        LinkMapper linkMapper = new LinkMapper();
        teamNoUserDTO.setTeamName(teamUsers.getTeams().getTeamName());
        teamNoUserDTO.setTeamDescription(teamUsers.getTeams().getTeamDescription());
        teamNoUserDTO.setLinks(
                teamUsers.getTeams()
                        .getLinks()
                        .stream()
                        .map(linkMapper::toLinkDTO)
                        .toList()
        );
        teamNoUserDTO.setUsers(
                teamUsers.getTeams()
                        .getUsers()
                        .stream()
                        .map(TeamUsers::getUsers)
                        .map(userMapper::toUserNoTeamDTO)
                        .toList()
        );
        return teamNoUserDTO;
    }
}
