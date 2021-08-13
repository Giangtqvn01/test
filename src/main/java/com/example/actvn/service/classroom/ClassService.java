package com.example.actvn.service.classroom;

import com.example.actvn.model.ResponseModel;
import com.example.actvn.model.classroom.ClassRequest;
import com.example.actvn.model.classroom.CreateNewClassRequest;
import com.example.actvn.model.classroom.UpdateClassRequest;
import com.example.actvn.security.UserPrincipal;

public interface ClassService {
    ResponseModel createNewClass(UserPrincipal userPrincipal, CreateNewClassRequest request);

    ResponseModel updateClass(UserPrincipal userPrincipal, UpdateClassRequest request);

    ResponseModel getListClass(UserPrincipal userPrincipal,Integer page, Integer size, ClassRequest request);
    ResponseModel geClassroomId(UserPrincipal userPrincipal,Integer id);
}
