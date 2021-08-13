package com.example.actvn.service.classroom;

import com.example.actvn.entity.Classroom;
import com.example.actvn.exception.BadRequestException;
import com.example.actvn.model.BaseModel;
import com.example.actvn.model.ResponseModel;
import com.example.actvn.model.classroom.ClassRequest;
import com.example.actvn.model.classroom.CreateNewClassRequest;
import com.example.actvn.model.classroom.UpdateClassRequest;
import com.example.actvn.model.response.PagedResponse;
import com.example.actvn.repository.classroom.ClassRepository;
import com.example.actvn.repository.classroom.ClassSpecification;
import com.example.actvn.repository.subject.SubjectsRepository;
import com.example.actvn.security.UserPrincipal;
import com.example.actvn.util.CommonUtils;
import com.example.actvn.util.Constant;
import com.example.actvn.util.Logit;
import com.example.actvn.util.PagedResponseMapper;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ClassServiceImpl implements ClassService {
    private static final Logit log = Logit.getInstance(ClassServiceImpl.class);
    @Autowired
    ClassRepository classRepository;
    @Autowired
    SubjectsRepository subjectsRepository;

    @Override
    public ResponseModel createNewClass(UserPrincipal userPrincipal, CreateNewClassRequest request) {
        ResponseModel responseModel = new ResponseModel();
        String message;
        try {
            Classroom classroom = new Classroom();
            classroom.setName(request.getName().trim());
            classroom.setActive(Constant.ACTIVE_FLG.NOT_DELETE);
            classroom.setNameKD(CommonUtils.removeAccent(request.getName().trim()));
            subjectsRepository.findById(request.getSubjectId()).ifPresent(classroom::setSubject);
            Classroom save = classRepository.save(classroom);

            log.info("Create classroom successfully!");
            message = "Create classroom successfully!";
            responseModel.setDescription(message);
            responseModel.setResponseStatus(HttpStatus.OK);
            responseModel.setData(save);
            return responseModel;
        } catch (RuntimeException e) {
            log.error(e.toString(), e);
            message = "Server error! Error: " + e.toString();
            BaseModel error = new BaseModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
            responseModel.setData(error);
            responseModel.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            responseModel.setDescription(message);
            return responseModel;
        }
    }

    @Override
    public ResponseModel updateClass(UserPrincipal userPrincipal, UpdateClassRequest request) {
        ResponseModel responseModel = new ResponseModel();
        String message;
        try {
            Classroom classroom = classRepository.findById(request.getId()).orElseThrow(() -> new BadRequestException("Not find classroom"));
            classroom.setActive(request.getActive() != null
                    && request.getActive().equals(Long.parseLong("1")) ?
                    Constant.ACTIVE_FLG.NOT_DELETE : Constant.ACTIVE_FLG.DELETE);
            classroom.setName(request.getName().trim());
            subjectsRepository.findById(request.getSubjectId()).ifPresent(classroom::setSubject);
            classroom.setNameKD(CommonUtils.removeAccent(request.getName()));
            classRepository.save(classroom);
            message = "Update classroom successfully!";
            responseModel.setDescription(message);
            responseModel.setResponseStatus(HttpStatus.OK);
            responseModel.setData(classroom);
            return responseModel;
        } catch (RuntimeException e) {
            message = "Server error! Error: " + e.toString();
            BaseModel error = new BaseModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
            responseModel.setData(error);
            responseModel.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            responseModel.setDescription(message);
            return responseModel;
        }
    }

    @Override
    public ResponseModel getListClass(UserPrincipal userPrincipal,Integer page, Integer size, ClassRequest request) {
        String message;
        ResponseModel model = new ResponseModel();
        try {
            if (page >= 1) page = page - 1;
            Pageable pageable = PageRequest.of(page, size);
            Page<Classroom> listClassroom = classRepository.findAll(ClassSpecification.classSpecification(userPrincipal,request), pageable);
            PagedResponse<T> pagedResponse = PagedResponseMapper.mapper(listClassroom);
            message = "Get class list successfully!";
            model.setData(pagedResponse);
            model.setDescription(message);
            model.setResponseStatus(HttpStatus.OK);
            return model;
        } catch (RuntimeException e) {
            log.error(e.toString(), e);
            message = "Server error! Get class list fails.";
            BaseModel error = new BaseModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
            model.setDescription(message);
            model.setData(error);
            model.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return model;
        }
    }

    @Override
    public ResponseModel geClassroomId(UserPrincipal userPrincipal, Integer id) {
        String message;
        ResponseModel model = new ResponseModel();
        try {

            Classroom classroom = classRepository.findById(Long.parseLong(id.toString())).orElseThrow(() -> new BadRequestException("Not found classroom"));
            message = "Get class list successfully!";
            model.setData(classroom);
            model.setDescription(message);
            model.setResponseStatus(HttpStatus.OK);
            return model;
        } catch (RuntimeException e) {
            log.error(e.toString(), e);
            message = "Server error! Get class list fails.";
            BaseModel error = new BaseModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
            model.setDescription(message);
            model.setData(error);
            model.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return model;
        }
    }
}
