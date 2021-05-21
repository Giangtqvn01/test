package com.example.actvn.security;

import com.example.actvn.entity.Account;
import lombok.Data;

@Data
public class JwtAuthenticationResponse {
    private String accessToken;
    private Account account;

    public JwtAuthenticationResponse(String accessToken,Account account) {
        this.accessToken = accessToken;
        this.account = account;
    }

    private String tokenType = "Bearer";

}
