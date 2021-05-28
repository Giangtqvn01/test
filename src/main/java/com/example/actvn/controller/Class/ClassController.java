package com.example.actvn.controller.Class;

import com.example.actvn.model.ResponseModel;
import com.example.actvn.model.classModel.ClassRequest;
import com.example.actvn.model.classModel.CreateNewClassRequest;
import com.example.actvn.model.classModel.UpdateClassRequest;
import com.example.actvn.security.CurrentUser;
import com.example.actvn.security.UserPrincipal;
import com.example.actvn.service.Class.ClassService;
import com.example.actvn.util.Constant;
import com.example.actvn.util.Logit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/class")
public class ClassController {
    private static final Logit log = Logit.getInstance(ClassController.class);

    @Autowired
    private ClassService classService;

    @PostMapping("/create-class")
    public ResponseEntity<?> createNewClass(@CurrentUser UserPrincipal userPrincipal, @RequestBody @Valid CreateNewClassRequest request) {
        log.info("Create new class ", request);
        long start = System.currentTimeMillis();
        ResponseModel response = classService.createNewClass(userPrincipal, request);
        long end = System.currentTimeMillis();
        long diff = end - start;
        log.info("Code = " + response.getResponseStatus() + "," + response.getDescription() + ",time = " + diff);
        return new ResponseEntity<>(response.getData(), response.getResponseStatus());
    }

    @PutMapping
    ResponseEntity<?> updateClass(@RequestBody @Valid UpdateClassRequest request, @CurrentUser UserPrincipal userPrincipal) {
        log.info("Update class", request);
        long start = System.currentTimeMillis();
        ResponseModel response = classService.updateClass(userPrincipal, request);
        long end = System.currentTimeMillis();
        long diff = end - start;
        log.info("Code = " + response.getResponseStatus() + " , " + response.getDescription() + " ,time = " + diff);
        return new ResponseEntity<>(response.getData(), response.getResponseStatus());
    }

    @PostMapping("/get-class")
    public ResponseEntity<?> getClass(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                   @RequestParam(value = "size", required = false, defaultValue = "20") Integer size,
                                   @RequestBody @Valid ClassRequest request) {
        if (page == null || (page != null && page <= 0)) page = Constant.PAGINATION.DEFAULT_PAGE;
        if (size == null) size = Constant.PAGINATION.DEFAULT_PAGE_SIZE;
        log.info("Get class ", request);
        long start = System.currentTimeMillis();
        ResponseModel response = classService.getListClass(page,size, request);
        long end = System.currentTimeMillis();
        long diff = end - start;
        log.info("Code = "+response.getResponseStatus() +" , "+response.getDescription()+" ,time = "+diff);
        return  new ResponseEntity<>(response.getData(),response.getResponseStatus());
    }
}