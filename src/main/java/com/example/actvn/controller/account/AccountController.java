package com.example.actvn.controller.account;

import com.example.actvn.model.ResponseModel;
import com.example.actvn.model.account.CreateAccountRequest;
import com.example.actvn.model.account.GetAccountRequest;
import com.example.actvn.model.account.UpdateAccountRequest;
import com.example.actvn.security.CurrentUser;
import com.example.actvn.security.UserPrincipal;
import com.example.actvn.service.account.AccountService;
import com.example.actvn.util.Constant;
import com.example.actvn.util.Logit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/account")
@RestController
public class AccountController {
    private static final Logit log = Logit.getInstance(AccountController.class);

    @Autowired
    private AccountService accountService;


    @PostMapping("/get-account")
    public ResponseEntity<?> getAccount(@RequestParam(name = "page", required = false) Integer page,
                                        @RequestParam(name = "size", required = false) Integer size,
                                        @RequestBody GetAccountRequest request) {
        log.info("Get account. Request : ", request);
        if (page == null || page <= 0) page = Constant.PAGINATION.DEFAULT_PAGE;
        if (size == null || size <= 0) size = Constant.PAGINATION.DEFAULT_PAGE_SIZE;
        long start = System.currentTimeMillis();
        ResponseModel model = accountService.getAccount(page, size, request);
        long end = System.currentTimeMillis();
        long diff = end - start;
        log.info("Code : " + model.getResponseStatus() + " , " + model.getDescription() + ", time :" + diff);
        return new ResponseEntity<>(model.getData(), model.getResponseStatus());
    }

    @PostMapping("/create-account")
    public ResponseEntity<?> createAccount(@RequestBody @Valid CreateAccountRequest request, @CurrentUser UserPrincipal userPrincipal) {
        log.info("Create new account request", request);
        long start = System.currentTimeMillis();
        ResponseModel model = accountService.createAccount(userPrincipal, request);
        long end = System.currentTimeMillis();
        long diff = end - start;
        log.info("Code : " + model.getResponseStatus() + ", " + model.getDescription() + ", time : " + diff);
        return new ResponseEntity<>(model.getData(), model.getResponseStatus());
    }

    @PutMapping("/update-account")
    public ResponseEntity<?> updateAccount(@RequestBody @Valid UpdateAccountRequest request, @CurrentUser UserPrincipal userPrincipal) {
        log.info("Update account request: ", request);
        long start = System.currentTimeMillis();
        ResponseModel model = accountService.updateAccount(request, userPrincipal);
        long end = System.currentTimeMillis();
        long diff = end - start;
        log.info("Code :"+model.getResponseStatus()+","+ model.getDescription()+", timer :"+diff);
        return  new ResponseEntity<>(model.getData(),model.getResponseStatus());
    }
}
