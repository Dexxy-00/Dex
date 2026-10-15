package com.dexxy.Dex.user.Controllers;

import com.dexxy.Dex.common.response.ApiResponse;
import com.dexxy.Dex.user.DTOs.UserResponse;
import com.dexxy.Dex.user.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<UserResponse>>> getUsers(Pageable pageable) {
        Page <UserResponse> users = userService.getUsers(pageable);
        ApiResponse<Page<UserResponse>> response = ApiResponse.<Page<UserResponse>>builder()
                .statusCode(HttpStatus.OK)
                .message("List of users retrieved successfully")
                .data(users)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> getUserById(@PathVariable Long id) {
        UserResponse user = userService.getUserById(id);
        ApiResponse<UserResponse> response = ApiResponse.<UserResponse>builder()
                .statusCode(HttpStatus.OK)
                .message("User retrieved successfully")
                .data(user)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<ApiResponse<UserResponse>> getUserByUsername(@PathVariable String username) {
        UserResponse user = userService.getUserByUsername(username);
        ApiResponse<UserResponse> response = ApiResponse.<UserResponse>builder()
                .statusCode(HttpStatus.OK)
                .message("User retrieved successfully")
                .data(user)
                .build();

        return ResponseEntity.ok(response);
    }
}