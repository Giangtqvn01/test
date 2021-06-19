package com.example.actvn.model.auth;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
public class LoginRequest {
    @NotBlank
    private String loginId;
    @NotBlank
    @Size(min = 6, max = 20)
    private String password;
}

