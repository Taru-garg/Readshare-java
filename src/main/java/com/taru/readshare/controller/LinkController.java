package com.taru.readshare.controller;

import com.taru.readshare.dto.link.LinkCreationDTO;
import com.taru.readshare.dto.link.LinkDTO;
import com.taru.readshare.dto.link.LinkMapper;
import com.taru.readshare.service.LinkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/links")
public class LinkController {
    private final LinkService linkService;

    private final LinkMapper linkMapper;

    public LinkController(LinkService linkService, LinkMapper linkMapper) {
        this.linkService = linkService;
        this.linkMapper = linkMapper;
    }

    @GetMapping("/")
    public ResponseEntity<List<LinkDTO>> getLinks() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        linkService.getLinks()
                                .stream()
                                .map(linkMapper::toLinkDTO)
                                .toList()
                );
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addLink(@Validated @RequestBody final LinkCreationDTO linkCreationDTO) {
        linkService.saveLink(linkCreationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Link added successfully");
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteLink(@PathVariable final UUID id) {
        linkService.deleteLink(id);
        return ResponseEntity.status(HttpStatus.OK).body("Link deleted successfully");
    }

    @GetMapping("/search")
    public ResponseEntity<List<LinkDTO>> searchLinks(@RequestParam("query") final String query,
                                                     @RequestParam(value = "teamId", required = false) final UUID teamId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        linkService.searchLinks(query, teamId)
                                .stream()
                                .map(linkMapper::toLinkDTO)
                                .toList()
                );
    }
}
