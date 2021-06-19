package com.example.actvn.security;

import com.example.actvn.entity.User;
import lombok.Data;

@Data
public class JwtAuthenticationResponse {
    private String accessToken;
    private User account;

    public JwtAuthenticationResponse(String accessToken,User account) {
        this.accessToken = accessToken;
        this.account = account;
    }

    private String tokenType = "Bearer";

}
