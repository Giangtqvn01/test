package com.example.actvn.controller.schedule;

import com.example.actvn.model.ResponseModel;
import com.example.actvn.model.schedule.CreateScheduleRequest;
import com.example.actvn.model.schedule.ScheduleRequest;
import com.example.actvn.model.schedule.UpdateScheduleRequest;
import com.example.actvn.security.CurrentUser;
import com.example.actvn.security.UserPrincipal;
import com.example.actvn.service.schedule.ScheduleService;
import com.example.actvn.util.Constant;
import com.example.actvn.util.Logit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {
    private static final Logit log = Logit.getInstance(ScheduleController.class);
    @Autowired
    ScheduleService scheduleService;

    @PostMapping("/create-schedule")
    public ResponseEntity<?> createSchedule(@CurrentUser UserPrincipal userPrincipal,
                                            @RequestBody @Valid CreateScheduleRequest request) {
        log.info("Create new schedule ", request);
        long start = System.currentTimeMillis();
        ResponseModel responseModel = scheduleService.createSchedule(request, userPrincipal);
        long end = System.currentTimeMillis();
        long diff = end - start;
        log.info("Code = " + responseModel.getResponseStatus() + " , " + responseModel.getDescription() + " ,time = " + diff);
        return new ResponseEntity<>(responseModel.getData(), responseModel.getResponseStatus());
    }

    @PutMapping("/update-schedule")
    public ResponseEntity<?> updateSchedule(@CurrentUser UserPrincipal userPrincipal,
                                            @RequestBody @Valid UpdateScheduleRequest request) {
        log.info("Update schedule ", request);
        long start = System.currentTimeMillis();
        ResponseModel responseModel = scheduleService.updateSchedule(request, userPrincipal);
        long end = System.currentTimeMillis();
        long diff = end - start;
        log.info("Code = " + responseModel.getResponseStatus() + " , " + responseModel.getDescription() + " ,time = " + diff);
        return new ResponseEntity<>(responseModel.getData(), responseModel.getResponseStatus());
    }

    @PostMapping("/get-schedule")
    public ResponseEntity<?> getSchedule(@CurrentUser UserPrincipal userPrincipal, @RequestBody @Valid ScheduleRequest request,
                                         @RequestParam(value = "page", required = false) Integer page,
                                         @RequestParam(value = "size", required = false) Integer size) {
        if (page == null || page <= 0) page = Constant.PAGINATION.DEFAULT_PAGE;
        if (size == null) size = Constant.PAGINATION.DEFAULT_PAGE_SIZE;
        log.info("Get schedule ", request);
        long start = System.currentTimeMillis();
        ResponseModel responseModel = scheduleService.getSchedule(userPrincipal, request,page,size);
        long end = System.currentTimeMillis();
        long diff = end - start;
        log.info("Code = " + responseModel.getResponseStatus() + ", " + responseModel.getDescription() + ", time= " + diff);
        return new ResponseEntity<>(responseModel.getData(), responseModel.getResponseStatus());
    }

    @GetMapping("/get-schedule/time")
    public ResponseEntity<?> getSchedule(@CurrentUser UserPrincipal userPrincipal,
                                         @RequestParam("ngay_bat_dau") String ngayBd,
                                         @RequestParam("ngay_ket_thuc") String ngayKT) {

        log.info("Get schedule time "+ ngayBd +" to "+ngayKT);
        long start = System.currentTimeMillis();
        ResponseModel responseModel = scheduleService.getScheduleTime(userPrincipal, ngayBd,ngayKT);
        long end = System.currentTimeMillis();
        long diff = end - start;
        log.info("Code = " + responseModel.getResponseStatus() + ", " + responseModel.getDescription() + ", time= " + diff);
        return new ResponseEntity<>(responseModel.getData(), responseModel.getResponseStatus());
    }
}
