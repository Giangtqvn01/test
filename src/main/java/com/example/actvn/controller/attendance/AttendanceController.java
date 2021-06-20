package com.example.actvn.controller.attendance;

import com.example.actvn.model.ResponseModel;
import com.example.actvn.model.attendance.CheckAttendanceRequest;
import com.example.actvn.model.attendance.GeneratorQrCodeRequest;
import com.example.actvn.security.CurrentUser;
import com.example.actvn.security.UserPrincipal;
import com.example.actvn.service.attendance.AttendanceService;
import com.example.actvn.util.Logit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {
    private static final Logit log = Logit.getInstance(AttendanceController.class);
    @Autowired
    AttendanceService attendanceService;

    @PostMapping("/generator-qrcode")
    public ResponseEntity<?> generatorQrCode(@CurrentUser UserPrincipal userPrincipal, @RequestBody GeneratorQrCodeRequest request) {
        log.info("Generator qr code");
        long start = System.currentTimeMillis();
        ResponseModel responseModel = attendanceService.generatorQrCode(userPrincipal, request);
        long end = System.currentTimeMillis();
        long diff = end - start;
        log.info("Code = " + responseModel.getResponseStatus() + ", " + responseModel.getDescription() + ", time= " + diff);
        return new ResponseEntity<>(responseModel.getData(), responseModel.getResponseStatus());
    }

    @PostMapping("/check-in")
    public ResponseEntity<?> checkAttendance(@CurrentUser UserPrincipal userPrincipal, @RequestBody @Valid CheckAttendanceRequest request) {
        log.info("Generator qr code");
        long start = System.currentTimeMillis();
        ResponseModel responseModel = attendanceService.checkAttendance(userPrincipal, request);
        long end = System.currentTimeMillis();
        long diff = end - start;
        log.info("Code = " + responseModel.getResponseStatus() + ", " + responseModel.getDescription() + ", time= " + diff);
        return new ResponseEntity<>(responseModel.getData(), responseModel.getResponseStatus());
    }

    @GetMapping("/attendance-statistics")
    public ResponseEntity<?> attendanceStatistics(@CurrentUser UserPrincipal userPrincipal) {
        log.info("Attendance statistics user : " + userPrincipal.getUsername());
        long start = System.currentTimeMillis();
        ResponseModel responseModel = attendanceService.attendanceStatistics(userPrincipal);
        long end = System.currentTimeMillis();
        long diff = end - start;
        log.info("Code = " + responseModel.getResponseStatus() + ", " + responseModel.getDescription() + ", time= " + diff);
        return new ResponseEntity<>(responseModel.getData(), responseModel.getResponseStatus());
    }

    @GetMapping("/history-attendance-schedule")
    public ResponseEntity<?> historyAttendanceSchedule(@CurrentUser UserPrincipal userPrincipal,
                                                       @RequestParam("classroom_id") Long classroomId) {
        log.info("Get history attendance-schedule");
        long start = System.currentTimeMillis();
        ResponseModel responseModel = attendanceService.historyAttendanceSchedule(userPrincipal, classroomId);
        long end = System.currentTimeMillis();
        long diff = end - start;
        log.info("Code = " + responseModel.getResponseStatus() + ", " + responseModel.getDescription() + ", time= " + diff);
        return new ResponseEntity<>(responseModel.getData(), responseModel.getResponseStatus());
    }
}
