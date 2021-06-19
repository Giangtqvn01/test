package com.example.actvn.model.attendance;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.sql.Timestamp;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GeneratorQrCodeRequest {
    private Timestamp timeBeganQrcode;
    private Timestamp qrcodeEndTime;
    private Long latitude; // kinh độ
    private Long longitude ; // vĩ độ
    private Long scheduleId;
}
