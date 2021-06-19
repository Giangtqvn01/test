package com.example.actvn.service.schedule;

import com.example.actvn.model.ResponseModel;
import com.example.actvn.model.schedule.CreateScheduleRequest;
import com.example.actvn.model.schedule.ScheduleRequest;
import com.example.actvn.model.schedule.UpdateScheduleRequest;
import com.example.actvn.security.UserPrincipal;

public interface ScheduleService {
    ResponseModel createSchedule(CreateScheduleRequest request, UserPrincipal userPrincipal);

    ResponseModel updateSchedule(UpdateScheduleRequest request, UserPrincipal userPrincipal);

    ResponseModel getSchedule(UserPrincipal userPrincipal, ScheduleRequest request,Integer page, Integer size);

    ResponseModel getScheduleTime(UserPrincipal userPrincipal, String ngayBd, String ngayKT);
}
