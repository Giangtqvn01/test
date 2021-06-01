package com.example.actvn.service.account;

import com.example.actvn.model.ResponseModel;
import com.example.actvn.model.account.CreateAccountRequest;
import com.example.actvn.model.account.GetAccountRequest;
import com.example.actvn.model.account.UpdateAccountRequest;
import com.example.actvn.security.UserPrincipal;

public interface AccountService {
    ResponseModel getAccount(Integer page, Integer size, GetAccountRequest request);

    ResponseModel createAccount(UserPrincipal userPrincipal, CreateAccountRequest request);

    ResponseModel updateAccount(UpdateAccountRequest request, UserPrincipal userPrincipal);
}
