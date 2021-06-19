package com.example.actvn.model.attendance;

import lombok.Data;

@Data
public class CheckInQRCodeRequest {
    private Long scheduleId;
    private Long qrCodeId;
    private Long latitude; // kinh độ
    private Long longitude ; // vĩ độ
    private String imei;
}
