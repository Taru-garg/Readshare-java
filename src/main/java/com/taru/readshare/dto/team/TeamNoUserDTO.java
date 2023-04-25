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
public class TeamNoUserDTO {
    String teamName;
    String teamDescription;
    List<LinkDTO> links;
    List<UserNoTeamDTO> users;
}
