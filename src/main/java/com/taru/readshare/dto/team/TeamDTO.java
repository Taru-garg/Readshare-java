package com.taru.readshare.dto.team;

import com.taru.readshare.dto.link.LinkDTO;
import com.taru.readshare.dto.user.UserNoTeamDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class TeamDTO {
    private String teamName;
    private String teamDescription;
    private List<LinkDTO> links;
    private List<UserNoTeamDTO> users;
}
