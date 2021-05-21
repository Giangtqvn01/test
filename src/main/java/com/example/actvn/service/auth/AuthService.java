package com.example.actvn.service.auth;

import com.example.actvn.entity.Account;
import com.example.actvn.model.ResponseModel;
import com.example.actvn.model.auth.CreateNewAccountRequest;

public interface AuthService {
    ResponseModel registerAccount(CreateNewAccountRequest request);
    Account getAccountInfoByLoginId(String loginid);
}
