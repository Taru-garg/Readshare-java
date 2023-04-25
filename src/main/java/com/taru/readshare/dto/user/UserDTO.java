package com.taru.readshare.dto.user;

import com.taru.readshare.dto.team.TeamNoUserDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class UserDTO {
    String username;
    String email;
    List<TeamNoUserDTO> teams;
}
