package com.example.actvn.model.attendance;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class HistoryAttendanceResponse {
    private String createDate;
    private String present;
    private String note;
    private Long id;
    private Long userId;
}
