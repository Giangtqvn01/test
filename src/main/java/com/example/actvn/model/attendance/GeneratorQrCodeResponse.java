package com.example.actvn.model.attendance;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.sql.Timestamp;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GeneratorQrCodeResponse {
    private Long timeBeganQrcode;
    private Long qrcodeEndTime;
    private Float latitude; // kinh độ
    private Float longitude ; // vĩ độ
    private Long scheduleId;
    private Long qrCodeId;
}
