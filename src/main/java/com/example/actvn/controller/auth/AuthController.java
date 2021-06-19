package com.example.actvn.controller.auth;


import com.example.actvn.entity.User;
import com.example.actvn.model.ResponseModel;
import com.example.actvn.model.auth.CreateNewAccountRequest;
import com.example.actvn.model.auth.LoginRequest;
import com.example.actvn.security.JwtAuthenticationResponse;
import com.example.actvn.security.JwtTokenProvider;
import com.example.actvn.service.auth.AuthService;
import com.example.actvn.util.Constant;
import com.example.actvn.util.Logit;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/api/auth")
@RestController
public class AuthController {
    private static final Logit log = Logit.getInstance(AuthController.class);
    final
    AuthService authService;
    final
    AuthenticationManager authenticationManager;
    final
    JwtTokenProvider tokenProvider;

    public AuthController(AuthService authService, AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerAccount(@Valid @RequestBody CreateNewAccountRequest request) {
        log.info("Create new account  =" + request.getFullName() + " user name=" + request.getLoginId());
        long start = System.currentTimeMillis();
        ResponseModel model = authService.registerAccount(request);
        long end = System.currentTimeMillis();
        long diff = end - start;
        log.info("Code = " + model.getResponseStatus() + "," + model.getDescription() + ",time = " + diff);
        return new ResponseEntity<>(model.getData(), model.getResponseStatus());
    }

    @PostMapping("/sign_in")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest request) {
        log.info("Login account  =" + request.getLoginId() + " user name=" + request.getPassword());
        long start = System.currentTimeMillis();
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getLoginId(), request.getPassword()));
            User account = authService.getAccountInfoByLoginId(request.getLoginId());
            if (account == null){
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
            if (Constant.ACTIVE_FLG.DELETE==account.getActive()) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }

            SecurityContextHolder.getContext().setAuthentication(authenticate);
            String jwt = tokenProvider.generateToken(authenticate);
            long end = System.currentTimeMillis();
            long diff = end - start;
            log.info("Token : " + jwt + ",time = " + diff);
            return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, account));
        } catch (Exception e) {
            log.info("account or password fails! ");
            log.info(e.getMessage(), e);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }
}
