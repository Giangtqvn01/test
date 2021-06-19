package com.example.actvn.model.auth;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@NoArgsConstructor
@AllArgsConstructor
public class CreateNewAccountRequest {
    @NotBlank
    private String fullName;
    @NotBlank
    private String loginId;
    @NotBlank
    @Size(min = 6, max = 20)
    private String password;

}
