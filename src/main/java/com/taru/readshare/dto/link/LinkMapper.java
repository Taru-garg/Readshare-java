package com.taru.readshare.dto.link;

import com.taru.readshare.entity.Link;
import com.taru.readshare.entity.Team;
import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;

@Component
public class LinkMapper {
    public Link toLink(@Nonnull LinkDTO linkdto) {
        Link link = new Link();
        link.setUrl(linkdto.getUrl());
        link.setTitle(linkdto.getTitle());
        link.setDescription(linkdto.getDescription());
        link.setTags(linkdto.getTags());
        return link;
    }

    public Link toLink(@Nonnull LinkCreationDTO linkCreationDTO) {
        Link link = new Link();
        Team team = new Team();
        team.setId(linkCreationDTO.getTeamId());
        link.setUrl(linkCreationDTO.getUrl());
        link.setTitle(linkCreationDTO.getTitle());
        link.setDescription(linkCreationDTO.getDescription());
        link.setTags(linkCreationDTO.getTags());
        link.setTeam(team);
        return link;
    }

    public LinkDTO toLinkDTO(@Nonnull Link link) {
        LinkDTO linkdto = new LinkDTO();
        linkdto.setUrl(link.getUrl());
        linkdto.setTitle(link.getTitle());
        linkdto.setDescription(link.getDescription());
        linkdto.setTags(link.getTags());
        return linkdto;
    }
}
