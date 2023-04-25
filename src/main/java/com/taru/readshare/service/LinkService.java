package com.taru.readshare.service;

import com.taru.readshare.dto.link.LinkCreationDTO;
import com.taru.readshare.dto.link.LinkMapper;
import com.taru.readshare.entity.Link;
import com.taru.readshare.entity.Team;
import com.taru.readshare.exceptions.LinkNotFoundException;
import com.taru.readshare.exceptions.TeamNotFoundException;
import com.taru.readshare.repository.LinkRepository;
import com.taru.readshare.repository.TeamRepository;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LinkService {
    private final LinkRepository linkRepository;
    private final TeamRepository teamRepository;
    private final LinkMapper linkMapper;

    public LinkService(LinkRepository linkRepository,
                       TeamRepository teamRepository) {
        this.linkRepository = linkRepository;
        this.teamRepository = teamRepository;
        this.linkMapper = new LinkMapper();
    }

    public void saveLink(@Nonnull LinkCreationDTO linkCreationDTO) throws TeamNotFoundException {
        Link link = linkMapper.toLink(linkCreationDTO);
        Optional<Team> team = teamRepository.findById(link.getTeam().getId());
        // TODO: check if user is part of the team
        if (team.isPresent()) {
            linkRepository.save(link);
        } else {
            throw new TeamNotFoundException();
        }
    }

    public List<Link> getLinks() {
        return linkRepository.findAll();
    }

    public void deleteLink(UUID id) throws LinkNotFoundException {
        // TODO: check if user ( the person deleting ) is admin of the team
        if (!linkRepository.existsById(id)) {
            throw new LinkNotFoundException();
        }
        linkRepository.deleteById(id);
    }

    public List<Link> searchLinks(String query, @Nullable UUID teamId) {
        // TODO: search only in teams that the user is part of
        if (teamId == null) {
            return linkRepository.searchAll(query);
        }
        return linkRepository.searchWithTeamId(query, teamId);
    }
}
