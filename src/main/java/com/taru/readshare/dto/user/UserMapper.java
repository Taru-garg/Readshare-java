package com.taru.readshare.dto.user;

import com.taru.readshare.dto.team.TeamMapper;
import com.taru.readshare.entity.User;
import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {
    public UserDTO toUserDTO(@Nonnull User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        if (user.getTeams().size() > 0) {
            TeamMapper teamMapper = new TeamMapper();
            userDTO.setTeams(
                    user.getTeams()
                            .stream()
                            .map(teamMapper::toTeamNoUserDTO)
                            .toList()
            );
        } else {
            userDTO.setTeams(List.of());
        }
        return userDTO;
    }

    public UserNoTeamDTO toUserNoTeamDTO(@Nonnull User user) {
        UserNoTeamDTO userNoTeamDTO = new UserNoTeamDTO();
        userNoTeamDTO.setUsername(user.getUsername());
        userNoTeamDTO.setEmail(user.getEmail());
        return userNoTeamDTO;
    }

    public User toUser(@Nonnull UserCreationDTO userCreationDTO) {
        User user = new User();
        user.setUsername(userCreationDTO.getUsername());
        user.setPassword(userCreationDTO.getPassword());
        user.setEmail(userCreationDTO.getEmail());
        return user;
    }
}
