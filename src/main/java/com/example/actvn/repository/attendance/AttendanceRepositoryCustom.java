package com.example.actvn.repository.attendance;

import com.example.actvn.model.attendance.AttendanceResponse;
import com.example.actvn.model.attendance.HistoryAttendanceScheduleResponse;

import java.util.List;

public interface AttendanceRepositoryCustom {
    List<AttendanceResponse> getAttendanceFlowUserId(Long userId);
    List<HistoryAttendanceScheduleResponse> getHistoryAttendanceSchedule(Long classroomId, Long userId);
}
