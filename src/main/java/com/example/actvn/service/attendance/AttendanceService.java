package com.example.actvn.service.attendance;

import com.example.actvn.model.ResponseModel;
import com.example.actvn.model.attendance.CheckAttendanceRequest;
import com.example.actvn.model.attendance.GeneratorQrCodeRequest;
import com.example.actvn.security.UserPrincipal;

public interface AttendanceService {
    ResponseModel generatorQrCode(UserPrincipal userPrincipal, GeneratorQrCodeRequest request);

    ResponseModel checkAttendance(UserPrincipal userPrincipal, CheckAttendanceRequest request);

    ResponseModel attendanceStatistics(UserPrincipal userPrincipal);

    ResponseModel historyAttendanceSchedule(UserPrincipal userPrincipal, Long classroomId);
}
