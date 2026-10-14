package com.dexxy.Dex.user.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {
    private final String accessToken;
    private final String tokenType;
    private final long expiresIn;
}
