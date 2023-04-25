package com.taru.readshare.service;

import com.taru.readshare.dto.team.TeamCreationDTO;
import com.taru.readshare.dto.team.TeamMapper;
import com.taru.readshare.entity.Team;
import com.taru.readshare.entity.TeamUsers;
import com.taru.readshare.exceptions.TeamNotFoundException;
import com.taru.readshare.exceptions.UserNotFoundException;
import com.taru.readshare.repository.TeamRepository;
import com.taru.readshare.repository.TeamUsersRepository;
import com.taru.readshare.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamUsersRepository teamUsersRepository;

    @Autowired
    private TeamMapper teamMapper;

    /**
     * @param teamCreationDTO Team to create
     * @return Created team
     */
    public Team createTeam(TeamCreationDTO teamCreationDTO) {
        Team team = teamMapper.toTeam(teamCreationDTO);
        teamRepository.save(team);
        return team;
    }

    /**
     * @return List of all teams
     */
    public List<Team> getTeams() {
        // TODO : return only teams that the user is a member of
        return teamRepository.findAll();
    }

    /**
     * @param id Team id to delete
     */
    public void deleteTeam(UUID id) {
        // TODO : delete team only if the user is the admin
        if (teamRepository.existsById(id)) {
            teamRepository.deleteById(id);
        } else {
            throw new TeamNotFoundException();
        }
    }

    /**
     * @param id Team id to get
     * @return Team with id
     */
    public Optional<Team> getTeam(UUID id) {
        // TODO : return team only if the user is a member of the team
        if (teamRepository.existsById(id)) {
            return teamRepository.findById(id);
        } else {
            throw new TeamNotFoundException();
        }
    }

    /**
     * @param id       Team id to add member to
     * @param memberId User id to add to team
     */
    public void addMember(UUID id, UUID memberId) {
        // TODO : add member only if the user is the admin
        // TODO : improve validations
        if (teamRepository.existsById(id)) {
            if (teamRepository.findById(id).isPresent()) {
                Team team = teamRepository.findById(id).get();
                if (userRepository.existsById(memberId)) {
                    TeamUsers teamUsers = new TeamUsers();
                    teamUsers.setTeams(team);
                    teamUsers.setUsers(userRepository.findById(memberId).get());
                    teamUsersRepository.save(teamUsers);
                } else {
                    throw new UserNotFoundException();
                }
            }
        } else {
            throw new TeamNotFoundException();
        }
    }

    /**
     * @param id       Team id to remove member from
     * @param memberId User id of the user to remove from team
     */
    public void removeMember(UUID id, UUID memberId) {
        // TODO : remove member only if the user is the admin
        // TODO : improve validations
        if (teamRepository.existsById(id)) {
            Team team = teamRepository.findById(id).get();
            if (userRepository.existsById(memberId)) {
                if (teamUsersRepository.existsByTeamIdAndUserId(id, memberId)) {
                    teamUsersRepository.deleteByTeamIdAndUserId(id, memberId);
                }
            } else {
                throw new UserNotFoundException();
            }
        } else {
            throw new TeamNotFoundException();
        }
    }
}
