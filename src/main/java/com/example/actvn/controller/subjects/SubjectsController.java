package com.example.actvn.controller.subjects;

import com.example.actvn.model.ResponseModel;
import com.example.actvn.model.subjects.CreateSubjectRequest;
import com.example.actvn.model.subjects.SubjectRequest;
import com.example.actvn.model.subjects.UpdateSubjectRequest;
import com.example.actvn.security.CurrentUser;
import com.example.actvn.security.UserPrincipal;
import com.example.actvn.service.subject.SubjectService;
import com.example.actvn.util.Constant;
import com.example.actvn.util.Logit;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/subjects")
public class SubjectsController {
    private static final Logit log = Logit.getInstance(SubjectsController.class);

    @Autowired
    private SubjectService subjectService;

    @PostMapping("/create-subject")
    public ResponseEntity<?> createSubject(@RequestBody @Valid CreateSubjectRequest request, @CurrentUser UserPrincipal userPrincipal) {
        log.info("Create subjects request : ", request);
        long start = System.currentTimeMillis();
        ResponseModel responseModel = subjectService.createSubjects(request, userPrincipal);
        long end = System.currentTimeMillis();
        long diff = end - start;
        log.info("Code :" + responseModel.getResponseStatus() + " , " + responseModel.getDescription() + ", time: " + diff);
        return new ResponseEntity<>(responseModel.getData(), responseModel.getResponseStatus());
    }

    @PutMapping("/update-subject")
    public ResponseEntity<?> updateSubject(@RequestBody @Valid UpdateSubjectRequest request, @CurrentUser UserPrincipal userPrincipal) {
        log.info("Update subject request : ", request);
        long start = System.currentTimeMillis();
        ResponseModel responseModel = subjectService.updateSubjects(request, userPrincipal);
        long end = System.currentTimeMillis();
        long diff = end - start;
        log.info("Code :" + responseModel.getResponseStatus() + ", " + responseModel.getDescription() + ", time: " + diff);
        return new ResponseEntity<>(responseModel.getData(), responseModel.getResponseStatus());
    }

    @PostMapping("/get-subject")
    public ResponseEntity<?> getSubject(@RequestBody SubjectRequest request, @CurrentUser UserPrincipal userPrincipal,
                                        @RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        log.info("Get subject request : ", request);
        if (page == null || page <= 0) page = Constant.PAGINATION.DEFAULT_PAGE;
        if (size == null) size = Constant.PAGINATION.DEFAULT_PAGE_SIZE;
        long start = System.currentTimeMillis();
        ResponseModel responseModel = subjectService.getSubjects(request, userPrincipal,page,size);
        long end = System.currentTimeMillis();
        long diff = end - start;
        log.info("Code :" + responseModel.getResponseStatus() + ", " + responseModel.getDescription() + ", time: " + diff);
        return new ResponseEntity<>(responseModel.getData(), responseModel.getResponseStatus());
    }
}
