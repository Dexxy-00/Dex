package com.dexxy.Dex.user.Controllers;

import com.dexxy.Dex.common.response.ApiResponse;
import com.dexxy.Dex.user.DTOs.LoginRequest;
import com.dexxy.Dex.user.DTOs.LoginResponse;
import com.dexxy.Dex.user.DTOs.RegisterRequest;
import com.dexxy.Dex.user.Services.AuthService;
import com.dexxy.Dex.user.DTOs.UserResponse;
import com.dexxy.Dex.user.Services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(
            @Valid @RequestBody LoginRequest request) {
        LoginResponse loginResponse = authService.login(request);
        ApiResponse<LoginResponse> response = ApiResponse.<LoginResponse>builder()
                .statusCode(HttpStatus.OK)
                .message("Login successful")
                .data(loginResponse)
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponse>> register(@Valid @RequestBody RegisterRequest request) {
        UserResponse user = userService.register(request);
        ApiResponse<UserResponse> response = ApiResponse.<UserResponse>builder()
                .statusCode(HttpStatus.CREATED)
                .message("User registered successfully")
                .data(user)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
