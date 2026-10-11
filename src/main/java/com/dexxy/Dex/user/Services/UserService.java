package com.dexxy.Dex.user.Services;

import com.dexxy.Dex.user.DTOs.UserResponse;
import com.dexxy.Dex.user.DTOs.RegisterRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    UserResponse register(RegisterRequest request);

    Page<UserResponse> getUsers(Pageable pageable);

    UserResponse getUserById(Long id);

    UserResponse getUserByUsername(String Username);
}
