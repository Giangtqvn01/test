package com.example.actvn.model.attendance;

import lombok.Data;

@Data
public class CheckInQRCodeRequest {
    private Long scheduleId;
    private Long qrCodeId;
    private Float latitude; // kinh độ
    private Float longitude ; // vĩ độ
    private String imei;
}
