package com.example.actvn.service.auth;


import com.example.actvn.entity.Role;
import com.example.actvn.entity.RoleName;
import com.example.actvn.entity.User;
import com.example.actvn.exception.AppException;
import com.example.actvn.model.BaseModel;
import com.example.actvn.model.ResponseModel;
import com.example.actvn.model.auth.CreateNewAccountRequest;
import com.example.actvn.repository.RoleRepository;
import com.example.actvn.repository.account.AccountRepository;
import com.example.actvn.util.CommonUtils;
import com.example.actvn.util.Constant;
import com.example.actvn.util.HtmlUtil;
import com.example.actvn.util.Logit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


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
            HtmlUtil.validateRequest(request);
            User account = new User();
            account.setUserName(request.getLoginId());
            account.setName(request.getFullName());
            account.setActive(Constant.ACTIVE_FLG.NOT_DELETE);
            account.setPassword(passwordEncoder.encode(request.getPassword()));
            account.setNameKD(CommonUtils.removeAccent(request.getFullName()));
            Role userRole = roleRepository.findByName(RoleName.SV.toString()).orElseThrow(() -> new AppException("User Role not set."));
            account.setRole(userRole);

            User save = accountRepository.save(account);
            log.info("Insert account for new store");
            log.info(" Account new :"+save.toString());
            message = "Created account success!";
            BaseModel success = new BaseModel(HttpStatus.OK.value(), message);
            model.setData(success);
            model.setDescription(message);
            model.setResponseStatus(HttpStatus.OK);
            return model;
        } catch (RuntimeException e) {
            log.error(e.toString(), e);
            BaseModel error = new BaseModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Server error!");
            ResponseModel model = new ResponseModel();
            model.setData(error);
            model.setDescription("Server error!");
            model.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return model;
        }
    }

    @Override
    public User getAccountInfoByLoginId(String loginid) {
        return accountRepository.findByUserName(loginid).orElse(null);
    }
}
