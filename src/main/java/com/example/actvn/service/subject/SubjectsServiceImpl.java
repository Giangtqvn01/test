package com.example.actvn.service.subject;

import com.example.actvn.model.ResponseModel;
import com.example.actvn.model.subjects.CreateSubjectRequest;
import com.example.actvn.model.subjects.SubjectRequest;
import com.example.actvn.model.subjects.UpdateSubjectRequest;
import com.example.actvn.repository.account.AccountRepository;
import com.example.actvn.repository.subject.SubjectsRepository;
import com.example.actvn.security.UserPrincipal;
import com.example.actvn.util.Logit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectsServiceImpl implements SubjectService {
    private static final Logit log = Logit.getInstance(SubjectsServiceImpl.class);
    @Autowired
    private SubjectsRepository subjectsRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public ResponseModel createSubjects(CreateSubjectRequest request, UserPrincipal userPrincipal) {
        return null;
    }

    @Override
    public ResponseModel updateSubjects(UpdateSubjectRequest request, UserPrincipal userPrincipal) {
        return null;
    }

    @Override
    public ResponseModel getSubjects(SubjectRequest request, UserPrincipal userPrincipal, Integer page, Integer size) {
       return null;
    }
}
