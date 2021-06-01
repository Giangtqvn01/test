package com.example.actvn.model.account;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CreateAccountRequest {
    @NotBlank
    private String hoVaTen;
    @NotBlank
    private String roleCd;
    @NotNull
    private Timestamp ngaySinh;
    private Integer lopId;
}
