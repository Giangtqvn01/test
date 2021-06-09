package com.example.actvn.service.subject;

import com.example.actvn.model.ResponseModel;
import com.example.actvn.model.subjects.CreateSubjectRequest;
import com.example.actvn.model.subjects.SubjectRequest;
import com.example.actvn.model.subjects.UpdateSubjectRequest;
import com.example.actvn.security.UserPrincipal;

public interface SubjectService {
    ResponseModel createSubjects(CreateSubjectRequest request, UserPrincipal userPrincipal);

    ResponseModel updateSubjects(UpdateSubjectRequest request, UserPrincipal userPrincipal);

    ResponseModel getSubjects(SubjectRequest request, UserPrincipal userPrincipal, Integer page, Integer size);
}
