package com.taru.readshare.controller;

import com.taru.readshare.dto.team.TeamCreationDTO;
import com.taru.readshare.dto.team.TeamDTO;
import com.taru.readshare.dto.team.TeamMapper;
import com.taru.readshare.entity.Team;
import com.taru.readshare.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private TeamMapper teamMapper;

    @PostMapping("/create")
    public ResponseEntity<TeamDTO> createTeam( @RequestBody TeamCreationDTO teamCreationDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                teamMapper.toTeamDTO(
                    teamService.createTeam(teamCreationDTO)
            )
        );
    }

    @GetMapping("/")
    public ResponseEntity<List<TeamDTO>> getTeams() {
        return ResponseEntity.status(HttpStatus.OK).body(
                teamService.getTeams()
                        .stream()
                        .map(teamMapper::toTeamDTO)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeam(@PathVariable UUID id) {
        if (teamService.getTeam(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(teamService.getTeam(id).get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable UUID id) {
        teamService.deleteTeam(id);
        return ResponseEntity.status(HttpStatus.OK).body("Team deleted successfully");
    }

    @PutMapping("/add-member/{id}")
    public ResponseEntity<?> addMember(@PathVariable UUID id, @RequestParam UUID memberId) {

        teamService.addMember(id, memberId);
        return ResponseEntity.status(HttpStatus.OK).body("Member added successfully");
    }

    @DeleteMapping("/remove-member/{id}")
    public ResponseEntity<?> removeMember(@PathVariable UUID id, @RequestParam UUID memberId) {
        teamService.removeMember(id, memberId);
        return ResponseEntity.status(HttpStatus.OK).body("Member removed successfully");
    }
}
