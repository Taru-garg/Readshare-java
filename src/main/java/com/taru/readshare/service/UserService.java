package com.taru.readshare.service;

import com.taru.readshare.dto.user.UserCreationDTO;
import com.taru.readshare.dto.user.UserMapper;
import com.taru.readshare.entity.User;
import com.taru.readshare.exceptions.UserAlreadyExistsException;
import com.taru.readshare.exceptions.UserNotFoundException;
import com.taru.readshare.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper UserMapper;

    public Optional<User> getUser(UUID id) {
        return this.userRepository.findById(id);
    }

    public User registerUser(UserCreationDTO userCreationDTO) {
        User user = UserMapper.toUser(userCreationDTO);
        if(userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException("Email already used");
        }
        if(userRepository.existsByUsername(user.getUsername())) {
            throw new UserAlreadyExistsException("Username already used");
        }
        return this.userRepository.save(user);
    }

    public void deleteUser(UUID id) {
        if (!this.userRepository.existsById(id)) {
            throw new UserNotFoundException();
        }
        this.userRepository.deleteById(id);
    }

}
