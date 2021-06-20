package com.example.actvn.model.attendance;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class HistoryAttendanceScheduleResponse {
    private Long userId;
    private Long scheduleId;
    private Long classroomId;
    private String classroomName;
    private String userName;
    private List<HistoryAttendanceResponse> historyAttendances;
}
