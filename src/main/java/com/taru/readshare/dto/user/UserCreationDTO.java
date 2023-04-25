package com.taru.readshare.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserCreationDTO {
    String username;
    String password;
    String email;
}
