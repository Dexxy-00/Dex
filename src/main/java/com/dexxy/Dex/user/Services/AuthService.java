package com.dexxy.Dex.user.Services;

import com.dexxy.Dex.user.DTOs.LoginRequest;
import com.dexxy.Dex.user.DTOs.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
}
