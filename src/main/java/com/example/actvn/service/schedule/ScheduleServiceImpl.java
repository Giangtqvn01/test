package com.example.actvn.service.schedule;

import com.example.actvn.entity.Schedule;
import com.example.actvn.model.BaseModel;
import com.example.actvn.model.ResponseModel;
import com.example.actvn.model.response.PagedResponse;
import com.example.actvn.model.schedule.CreateScheduleRequest;
import com.example.actvn.model.schedule.ScheduleRequest;
import com.example.actvn.model.schedule.UpdateScheduleRequest;
import com.example.actvn.repository.schedule.ScheduleRepository;
import com.example.actvn.repository.schedule.ScheduleSpecification;
import com.example.actvn.security.UserPrincipal;
import com.example.actvn.util.DateUtils;
import com.example.actvn.util.PagedResponseMapper;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;
    @Override
    public ResponseModel createSchedule(CreateScheduleRequest request, UserPrincipal userPrincipal) {
        return null;
    }

    @Override
    public ResponseModel updateSchedule(UpdateScheduleRequest request, UserPrincipal userPrincipal) {
        return null;
    }

    @Override
    public ResponseModel getSchedule(UserPrincipal userPrincipal, ScheduleRequest request,Integer page, Integer size) {
        ResponseModel responseModel = new ResponseModel();
        String message;
        try {
            if (page >= 1) page = page - 1;
            Pageable pageable = PageRequest.of(page, size);
            Page<Schedule> listClassroom = scheduleRepository.findAll(ScheduleSpecification.searchSchedule(request), pageable);
            PagedResponse<T> pagedResponse = PagedResponseMapper.mapper(listClassroom);
            message = "Get class list successfully!";
            responseModel.setData(pagedResponse);
            responseModel.setDescription(message);
            responseModel.setResponseStatus(HttpStatus.OK);
            return responseModel;

        }catch (RuntimeException e){
            message="Server error! Error: "+e;
            BaseModel error = new BaseModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
            responseModel.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            responseModel.setDescription(message);
            responseModel.setData(error);
            return responseModel;
        }
    }

    @Override
    public ResponseModel getScheduleTime(UserPrincipal userPrincipal, String ngayBd, String ngayKT) {
        ResponseModel responseModel = new ResponseModel();
        String message;
        try {
            Date ngayBatDau = DateUtils.convertStringToDate(ngayBd, DateUtils.PATTERN_DD_MM_YYYY);
            LocalDate dateNgayBatDau = ngayBatDau.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            Date ngayKetThuc = DateUtils.convertStringToDate(ngayKT, DateUtils.PATTERN_DD_MM_YYYY);
            LocalDate dateNgayKt = ngayKetThuc.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int monthBd = dateNgayBatDau.getMonthValue();
            int dayBd = dateNgayBatDau.getDayOfMonth();
            int yearBd = dateNgayBatDau.getYear();
            int monthKt = dateNgayKt.getMonthValue();
            int dayKt = dateNgayKt.getDayOfMonth();
            int yearKt = dateNgayKt.getYear();
            List<Schedule> listClassroom = scheduleRepository.getScheduleByDateTime(dayBd, dayKt,monthBd,monthKt, yearBd, yearKt, userPrincipal.getAccountId());
            message = "Get class list successfully!";
            responseModel.setData(listClassroom);
            responseModel.setDescription(message);
            responseModel.setResponseStatus(HttpStatus.OK);
            return responseModel;

        }catch (RuntimeException e){
            message="Server error! Error: "+e;
            BaseModel error = new BaseModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
            responseModel.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            responseModel.setDescription(message);
            responseModel.setData(error);
            return responseModel;
        }
    }
}
