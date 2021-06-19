package com.example.actvn.service.account;

import com.example.actvn.model.ResponseModel;
import com.example.actvn.model.account.CreateAccountRequest;
import com.example.actvn.model.account.GetAccountRequest;
import com.example.actvn.model.account.UpdateAccountRequest;
import com.example.actvn.repository.RoleRepository;
import com.example.actvn.repository.account.AccountRepository;
import com.example.actvn.repository.classroom.ClassRepository;

import com.example.actvn.security.UserPrincipal;

import com.example.actvn.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    private static final Logit log = Logit.getInstance(AccountServiceImpl.class);
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ClassRepository classRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public ResponseModel getAccount(Integer page, Integer size, GetAccountRequest request) {
return null;
    }

    /***
     * create account
     * @param userPrincipal account
     * @param request new account
     * @return
     */
    @Override
    public ResponseModel createAccount(UserPrincipal userPrincipal, CreateAccountRequest request) {
       return null;
    }

    /***
     * create account teacher
     * @param userPrincipal account create information
     * @param request teacher account information
     * @return ResponseModel
     */
    private ResponseModel createTeacherAccount(UserPrincipal userPrincipal, CreateAccountRequest request) {
       return null;
    }

    private String generateRandomCodeTeacher(List<String> codeTeacher) {
        String maGV;
        boolean exitCodeTeacher;
        do {
            maGV = "GV" + CommonUtils.generateRandomCode(6);
            String finalMaGV = maGV;
            exitCodeTeacher = codeTeacher.stream().anyMatch(s -> s.equalsIgnoreCase(finalMaGV));
        } while (exitCodeTeacher);
        return maGV;
    }

    /***
     * create account student
     * add student in class
     * @param userPrincipal account create information
     * @param request student account information
     * @return ResponseModel
     */
    private ResponseModel createStudentAccount(UserPrincipal userPrincipal, CreateAccountRequest request) {
      return null;
    }


    /***
     * Update account
     * @param request account
     * @param userPrincipal account
     * @return ResponseModel
     */
    @Override
    public ResponseModel updateAccount(UpdateAccountRequest request, UserPrincipal userPrincipal) {
       return null;
    }
}
