package com.taru.readshare.dto.link;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.net.URL;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@Data
public class LinkCreationDTO {
    @NotBlank(message = "URL is required")
    private URL url;
    @NotBlank(message = "Title is required")
    private String title;
    private String description;
    private List<String> tags;
    @NotBlank(message = "Team ID is required")
    private UUID teamId;
}
