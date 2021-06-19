package com.example.actvn.model.attendance;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class AttendanceResponse {
    String classroomName;
    Long sumAttendance;
    Long userAttendance;
    Long classroomId;
}
