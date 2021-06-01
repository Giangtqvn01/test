package com.example.actvn.model.account;

import com.example.actvn.entity.AccountDetail;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UpdateAccountRequest {
    @NotNull
    private Integer id;
    private String hoVaTen;
    private Timestamp ngaySinh;
    private AccountDetail accountDetail;
}
