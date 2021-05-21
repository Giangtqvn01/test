package com.example.actvn.model.auth;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
public class LoginRequest {
    @NotBlank
    private String loginId;
    @NotBlank
    private String password;
}

