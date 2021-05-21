package com.example.actvn.service.auth;

import com.example.actvn.entity.Account;
import com.example.actvn.entity.Role;
import com.example.actvn.entity.RoleName;
import com.example.actvn.exception.AppException;
import com.example.actvn.model.BaseModel;
import com.example.actvn.model.ResponseModel;
import com.example.actvn.model.auth.CreateNewAccountRequest;
import com.example.actvn.repository.AccountRepository;
import com.example.actvn.repository.RoleRepository;
import com.example.actvn.util.Constant;
import com.example.actvn.util.HtmlUtil;
import com.example.actvn.util.Logit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
public class AuthServiceImpl implements AuthService {
    private static final Logit log = Logit.getInstance(AuthServiceImpl.class);
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public ResponseModel registerAccount(CreateNewAccountRequest request) {
        try {
            ResponseModel model = new ResponseModel();
            String message = "";
            request = (CreateNewAccountRequest) HtmlUtil.validateRequest(request);
            Account account = new Account();
            account.setTaiKhoan(request.getTaiKhoan());
            account.setHoVaTen(request.getHoVaTen());
            account.setHieuLuc(Constant.ACTIVE_FLG.NOT_DELETE);
            account.setRoleCd(Constant.ROLE.ADMIN);
            account.setPassword(passwordEncoder.encode(request.getMatKhau()));
            Role userRole = roleRepository.findByRoleCd(RoleName.ADMIN).orElseThrow(() -> new AppException("User Role not set."));
            account.setRoles(Collections.singleton(userRole));
            account.setNguoiTao("QTHT");
            Account save = accountRepository.save(account);
            log.info("Insert account for new store");
            log.info(" Account new :"+save.toString());
            message = "Created account success!";
            BaseModel success = new BaseModel(HttpStatus.OK.value(), message);
            model.setData(success);
            model.setDescription(message);
            model.setResponseStatus(HttpStatus.OK);
            return model;
        } catch (RuntimeException e) {
            BaseModel error = new BaseModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Server error!");
            ResponseModel model = new ResponseModel();
            model.setData(error);
            model.setDescription("Server error!");
            model.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return model;
        }
    }

    @Override
    public Account getAccountInfoByLoginId(String loginid) {
        return accountRepository.findByTaiKhoanAndHieuLuc(loginid, Constant.ACTIVE_FLG.NOT_DELETE).orElse(null);
    }
}
