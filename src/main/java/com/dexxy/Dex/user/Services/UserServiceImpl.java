package com.dexxy.Dex.user.Services;

import com.dexxy.Dex.common.exceptions.ResourceNotFoundException;
import com.dexxy.Dex.common.exceptions.ResourceAlreadyExistsException;
import com.dexxy.Dex.user.DTOs.RegisterRequest;
import com.dexxy.Dex.user.DTOs.UserResponse;
import com.dexxy.Dex.user.Entities.UserEntity;
import com.dexxy.Dex.user.Repositories.UserRepository;
import com.dexxy.Dex.user.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse register(RegisterRequest request) {
        String username = request.getUsername().trim();
        String email = request.getEmail().trim().toLowerCase(Locale.ROOT);

        if (userRepository.existsByUsername(username)) {
            throw new ResourceAlreadyExistsException("Username is already taken");
        }
        if (userRepository.existsByEmail(email)) {
            throw new ResourceAlreadyExistsException("Email is already registered");
        }

        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setDisplayName(request.getDisplayName().trim());
        user.setEmail(email);
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);
        user.setEnabled(true);

        UserEntity savedUser = userRepository.save(user);
        log.info("Registered user with id {}", savedUser.getId());
        return modelMapper.map(savedUser, UserResponse.class);
    }

    @Override
    public Page<UserResponse> getUsers(Pageable pageable) {
        log.debug("Fetching all users : page = {}, size = {}", pageable.getPageNumber(), pageable.getPageSize());
        Page<UserEntity> users = userRepository.findAll(pageable);
        return users.map(user -> modelMapper.map(user, UserResponse.class));
    }

    @Override
    public UserResponse getUserById(Long id) {
        log.debug("Fetching user : id = {}", id);
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("User with id {} not found", id);
                    return new ResourceNotFoundException("User with ID " + id + " does not exist");
                });
        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public UserResponse getUserByUsername(String username) {
        log.debug("Fetching user : username = {}", username);
        UserEntity user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> {
                    log.warn("User with username {} not found", username);
                    return new ResourceNotFoundException("User with Username " + username + " does not exist");
                });
        return modelMapper.map(user, UserResponse.class);
    }
}
