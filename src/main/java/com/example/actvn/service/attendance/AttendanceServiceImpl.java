package com.example.actvn.service.attendance;

import com.example.actvn.entity.Attendance;
import com.example.actvn.entity.Classroom;
import com.example.actvn.entity.QrInfo;
import com.example.actvn.entity.Schedule;
import com.example.actvn.exception.BadRequestException;
import com.example.actvn.model.BaseModel;
import com.example.actvn.model.ResponseModel;
import com.example.actvn.model.attendance.*;
import com.example.actvn.repository.attendance.AttendanceRepository;
import com.example.actvn.repository.classroom.ClassRepository;
import com.example.actvn.repository.qrinfo.QRInfoRepository;
import com.example.actvn.repository.schedule.ScheduleRepository;
import com.example.actvn.security.UserPrincipal;
import com.example.actvn.util.AES;
import com.example.actvn.util.MapsUtil;
import com.example.actvn.util.QRCodeGenerator;
import com.google.gson.Gson;
import com.google.zxing.WriterException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class AttendanceServiceImpl implements AttendanceService {
    private static Gson gson = new Gson();
    @Value("${app.ase.secretKey}")
    private String secretKey;
    @Autowired
    AttendanceRepository attendanceRepository;
    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    QRInfoRepository qrInfoRepository;
    @Autowired
    ClassRepository classRepository;

    @Override
    public ResponseModel generatorQrCode(UserPrincipal userPrincipal, GeneratorQrCodeRequest request) {
        ResponseModel responseModel = new ResponseModel();
        String message;
        try {
//            Classroom classroom = classRepository.findById(request.getClassroomId()).orElseThrow(() -> new BadRequestException("Not found schedule "));

            Schedule schedule = scheduleRepository.findById(request.getScheduleId()).orElseThrow(() -> new BadRequestException("Not found schedule "));
            if (schedule == null) {
                message = "Not found schedule";
                BaseModel error = new BaseModel(HttpStatus.BAD_REQUEST.value(), message);
                responseModel.setData(error);
                responseModel.setDescription(message);
                responseModel.setResponseStatus(HttpStatus.BAD_REQUEST);
                return responseModel;
            }
            if (!checkTimeGeneraterQRCode(schedule)) {
                message = "Time out to generate attendance code";
                BaseModel error = new BaseModel(HttpStatus.BAD_REQUEST.value(), message);
                responseModel.setData(error);
                responseModel.setDescription(message);
                responseModel.setResponseStatus(HttpStatus.BAD_REQUEST);
                return responseModel;
            }

            QrInfo qrInfo = new QrInfo();
            qrInfo.setScheduleId(request.getScheduleId());
            if (request.getTimeBeganQrcode() == null) {
                request.setTimeBeganQrcode(new Timestamp(System.currentTimeMillis()));
            }
            if (request.getQrcodeEndTime() == null) {
                request.setQrcodeEndTime(new Timestamp(request.getTimeBeganQrcode().getTime() + TimeUnit.HOURS.toMillis(1)));
            }
            qrInfo.setQrcodeEndTime(request.getQrcodeEndTime());
            qrInfo.setTimeBeganQrcode(request.getTimeBeganQrcode());
            qrInfo.setLatitude(request.getLatitude());
            qrInfo.setLongitude(request.getLongitude());
            QrInfo saveQRCode = qrInfoRepository.save(qrInfo);

            GeneratorQrCodeResponse response = new GeneratorQrCodeResponse();
            response.setScheduleId(request.getScheduleId());
            response.setLatitude(request.getLatitude());
            response.setLongitude(request.getLongitude());
            response.setTimeBeganQrcode(request.getTimeBeganQrcode().getTime());
            response.setQrcodeEndTime(request.getQrcodeEndTime().getTime());
            response.setQrCodeId(saveQRCode.getId());

            String responseData = gson.toJson(response);
            String encrypt = AES.encrypt(responseData, secretKey);

            QRCodeResponse qrCodeResponse = new QRCodeResponse();
            String base64 = QRCodeGenerator.getQRCodeImage(encrypt, 600, 600);
            qrCodeResponse.setImage(base64);

            message = "Create qr code successfully!";
            responseModel.setResponseStatus(HttpStatus.OK);
            responseModel.setData(qrCodeResponse);
            responseModel.setDescription(message);
            return responseModel;
        } catch (RuntimeException | WriterException | IOException e) {
            message = "Server error! Error: " + e;
            BaseModel error = new BaseModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
            responseModel.setData(error);
            responseModel.setDescription(message);
            responseModel.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return responseModel;
        }
    }

    private boolean checkTimeGeneraterQRCode(Schedule schedule) {
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1; // Note: zero based!
        int day = now.get(Calendar.DAY_OF_MONTH);
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);
        if (schedule.getDate() == day && year == schedule.getYear() && month == schedule.getMonth()) {
            String[] startTime = schedule.getStartTime().split(":");
            String[] endTime = schedule.getEndTime().split(":");
            if (hour < Integer.parseInt(startTime[0])) {
                return false;
            }
            if (hour > Integer.parseInt(endTime[0])) {
                return false;
            }
            if (hour == Integer.parseInt(endTime[0]) && minute > Integer.parseInt(endTime[1])) {
                return false;
            }
            if (hour == Integer.parseInt(startTime[0]) && minute < Integer.parseInt(startTime[1])) {
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public ResponseModel checkAttendance(UserPrincipal userPrincipal, CheckAttendanceRequest request) {
        ResponseModel responseModel = new ResponseModel();
        String message;
        try {
            String decrypt = AES.decrypt(request.getData(), secretKey);
            CheckInQRCodeRequest response = gson.fromJson(decrypt, CheckInQRCodeRequest.class);
            if (StringUtils.isEmpty(response.getImei())) {
                message = "Imei not null ";
                BaseModel error = new BaseModel(HttpStatus.BAD_REQUEST.value(), message);
                responseModel.setData(error);
                responseModel.setDescription(message);
                responseModel.setResponseStatus(HttpStatus.BAD_REQUEST);
                return responseModel;
            }

            QrInfo qrInfo = qrInfoRepository.findById(response.getQrCodeId())
                    .orElseThrow(() -> new BadRequestException("Not found QR code !"));
            if (!checkDistanceAttendance(response, qrInfo)) {
                message = "You are currently out of attendance range";
                BaseModel error = new BaseModel(HttpStatus.BAD_REQUEST.value(), message);
                responseModel.setData(error);
                responseModel.setDescription(message);
                responseModel.setResponseStatus(HttpStatus.BAD_REQUEST);
                return responseModel;
            }
            if (!checkActiveQRCode(qrInfo)) {
                message = "Check-in time is over";
                BaseModel error = new BaseModel(HttpStatus.BAD_REQUEST.value(), message);
                responseModel.setData(error);
                responseModel.setDescription(message);
                responseModel.setResponseStatus(HttpStatus.BAD_REQUEST);
                return responseModel;
            }


            String checkImeiDevice = checkImei(response.getImei());
//            if (!StringUtils.isEmpty(checkImeiDevice)) {
//                message = "Your device has been registered in a different account with a student code : ";
//                BaseModel error = new BaseModel(HttpStatus.BAD_REQUEST.value(), message);
//                responseModel.setData(error);
//                responseModel.setDescription(message);
//                responseModel.setResponseStatus(HttpStatus.BAD_REQUEST);
//                return responseModel;
//            }
            Attendance attendance = new Attendance();
            attendance.setUserId(userPrincipal.getAccountId());
            attendance.setScheduleId(response.getScheduleId());
            attendance.setImei(response.getImei().trim());

            Attendance save = attendanceRepository.save(attendance);

            message = "Create qr code successfully!";
            responseModel.setResponseStatus(HttpStatus.OK);
            responseModel.setData(save);
            responseModel.setDescription(message);
            return responseModel;
        } catch (RuntimeException e) {
            message = "Server error! Error: " + e;
            BaseModel error = new BaseModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
            responseModel.setData(error);
            responseModel.setDescription(message);
            responseModel.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return responseModel;
        }
    }

    private boolean checkActiveQRCode(QrInfo qrInfo) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);
        if (timestamp.after(qrInfo.getTimeBeganQrcode()) && timestamp.before(qrInfo.getQrcodeEndTime())) {
            return true;
        }
        return false;
    }

    private boolean checkDistanceAttendance(CheckInQRCodeRequest request, QrInfo qrInfo) {
        double distance = MapsUtil.distanceBetween2Points(qrInfo.getLatitude(), qrInfo.getLongitude(),
                request.getLatitude(), request.getLongitude());
        if (distance >= 0 && distance < 11) {
            return true;
        } else {
            return false;
        }
    }

    private String checkImei(String imei) {
        return "Your device has been registered in a different account with a student code : ";
    }

    @Override
    public ResponseModel attendanceStatistics(UserPrincipal userPrincipal) {
        ResponseModel responseModel = new ResponseModel();
        String message;
        try {
            List<AttendanceResponse> attendance = attendanceRepository.getAttendanceFlowUserId(userPrincipal.getAccountId());
            message = "Get attendance statistics successfully!";
            responseModel.setDescription(message);
            responseModel.setResponseStatus(HttpStatus.OK);
            responseModel.setData(attendance);
            return responseModel;
        } catch (RuntimeException e) {
            message = "Server error! Error: " + e.getMessage();
            BaseModel error = new BaseModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
            responseModel.setDescription(message);
            responseModel.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            responseModel.setData(error);
            return responseModel;
        }
    }

    @Override
    public ResponseModel historyAttendanceSchedule(UserPrincipal userPrincipal, Long classroomId) {
        ResponseModel responseModel = new ResponseModel();
        String message;
        try {
            List<HistoryAttendanceScheduleResponse> historyAttendanceSchedule = attendanceRepository.getHistoryAttendanceSchedule(classroomId, null);
            message = "Get attendance statistics successfully!";
            responseModel.setDescription(message);
            responseModel.setResponseStatus(HttpStatus.OK);
            responseModel.setData(historyAttendanceSchedule);
            return responseModel;
        } catch (RuntimeException e) {
            message = "Server error! Error: " + e.getMessage();
            BaseModel error = new BaseModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
            responseModel.setDescription(message);
            responseModel.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            responseModel.setData(error);
            return responseModel;
        }
    }

    @Override
    public ResponseModel historyAttendanceUser(UserPrincipal userPrincipal, Long classroomId) {
        ResponseModel responseModel = new ResponseModel();
        String message;
        try {
            List<HistoryAttendanceScheduleResponse> historyAttendanceSchedule = attendanceRepository.getHistoryAttendanceSchedule(classroomId, userPrincipal.getAccountId());
            message = "Get attendance statistics successfully!";
            responseModel.setDescription(message);
            responseModel.setResponseStatus(HttpStatus.OK);
            responseModel.setData(historyAttendanceSchedule);
            return responseModel;
        } catch (RuntimeException e) {
            message = "Server error! Error: " + e.getMessage();
            BaseModel error = new BaseModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
            responseModel.setDescription(message);
            responseModel.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            responseModel.setData(error);
            return responseModel;
        }
    }

    public static void main(String[] args) {
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1; // Note: zero based!
        int day = now.get(Calendar.DAY_OF_MONTH);
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);
        int second = now.get(Calendar.SECOND);
        int millis = now.get(Calendar.MILLISECOND);
        switch (hour) {
            case 7:
            case 8: {
                hour = 7;
                minute = 0;
                second = 0;
                break;
            }
            case 9: {
                if (minute < 30) {
                    hour = 7;
                    minute = 0;
                    second = 0;
                } else {
                    hour = 9;
                    minute = 30;
                    second = 0;
                }
                break;
            }
            case 10:
            case 11: {
                hour = 9;
                minute = 30;
                second = 0;
                break;
            }
            case 12: {
                if (minute < 30) {
                    hour = 9;
                    minute = 30;
                    second = 0;
                } else {
                    hour = 12;
                    minute = 30;
                    second = 0;
                }
                break;
            }
            case 13:
            case 14: {
                hour = 12;
                minute = 30;
                second = 0;
                break;
            }
            case 15:
            case 16:
            case 17: {
                hour = 15;
                minute = 0;
                second = 0;
                break;
            }
            case 18:
            case 19:
            case 20:
            case 21: {
                hour = 18;
                minute = 0;
                second = 0;
                break;
            }
        }

        System.out.printf("%d-%02d-%02d %02d:%02d:%02d", year, month, day, hour, minute, second);
    }
}
