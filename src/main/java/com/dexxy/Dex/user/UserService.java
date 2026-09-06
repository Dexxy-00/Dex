package com.dexxy.Dex.user;

import com.dexxy.Dex.user.DTOs.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<UserResponse> getUsers(Pageable pageable);

    UserResponse getUserById(Long id);

    UserResponse getUserByUsername(String Username);
}
