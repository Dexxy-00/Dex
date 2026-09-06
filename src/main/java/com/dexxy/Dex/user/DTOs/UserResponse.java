package com.dexxy.Dex.user.DTOs;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponse {
    private Long id;
    private String username;
    private String displayName;
    private String bio;
    private String avatarUrl;
    private LocalDateTime createdAt;
}
