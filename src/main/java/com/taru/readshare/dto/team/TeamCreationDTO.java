package com.taru.readshare.dto.team;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class TeamCreationDTO {
    @NotBlank(message = "Team name is required")
    private String teamName;
    private String teamDescription;
}
