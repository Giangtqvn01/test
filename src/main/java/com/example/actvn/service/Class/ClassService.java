package com.example.actvn.service.Class;

import com.example.actvn.model.ResponseModel;
import com.example.actvn.model.classModel.ClassRequest;
import com.example.actvn.model.classModel.CreateNewClassRequest;
import com.example.actvn.model.classModel.UpdateClassRequest;
import com.example.actvn.security.UserPrincipal;

public interface ClassService {
    ResponseModel createNewClass(UserPrincipal userPrincipal, CreateNewClassRequest request);

    ResponseModel updateClass(UserPrincipal userPrincipal, UpdateClassRequest request);

    ResponseModel getListClass(Integer page, Integer size, ClassRequest request);
}
