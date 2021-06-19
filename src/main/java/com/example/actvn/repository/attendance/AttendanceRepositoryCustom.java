package com.example.actvn.repository.attendance;

import com.example.actvn.model.attendance.AttendanceResponse;

import java.util.List;

public interface AttendanceRepositoryCustom {
    List<AttendanceResponse> getAttendanceFlowUserId(Long userId);
}
