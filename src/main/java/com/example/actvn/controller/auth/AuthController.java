package com.example.actvn.controller.auth;

import com.example.actvn.entity.Account;
import com.example.actvn.model.ResponseModel;
import com.example.actvn.model.auth.CreateNewAccountRequest;
import com.example.actvn.model.auth.LoginRequest;
import com.example.actvn.security.JwtAuthenticationResponse;
import com.example.actvn.security.JwtTokenProvider;
import com.example.actvn.service.auth.AuthService;
import com.example.actvn.util.Logit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
    private static Logit log = Logit.getInstance(AuthController.class);
    @Autowired
    AuthService authService;
    @Autowired
    AuthenticationManagerBuilder authenticationManagerBuilder;
    @Autowired
    JwtTokenProvider tokenProvider;


    @PostMapping("/signup")
    public ResponseEntity registerAccount(@Valid @RequestBody CreateNewAccountRequest request) {
        log.info("Create new account  =" + request.getHoVaTen() + " user name=" + request.getTaiKhoan());
        long start = System.currentTimeMillis();
        ResponseModel model = authService.registerAccount(request);
        long end = System.currentTimeMillis();
        long diff = end - start;
        log.info("Code = " + model.getResponseStatus() + "," + model.getDescription() + ",time = " + diff);
        return new ResponseEntity(model.getData(), model.getResponseStatus());
    }

    @PostMapping("/signin")
    public ResponseEntity authenticateUser(@Valid @RequestBody LoginRequest request) {
        log.info("Login account  =" + request.getLoginId() + " user name=" + request.getPassword());
        long start = System.currentTimeMillis();
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getLoginId(), request.getPassword());
            Account account = authService.getAccountInfoByLoginId(request.getLoginId());
            if (account == null) {
                return ResponseEntity.ok(account);
            }
            authenticationToken.setDetails(account);

            Authentication authenticate = authenticationManagerBuilder.getObject()
                    .authenticate(authenticationToken);

            SecurityContextHolder.getContext().setAuthentication(authenticate);
            String jwt = tokenProvider.generateToken(authenticate);
            long end = System.currentTimeMillis();
            long diff = end - start;
            log.info("Token : " + jwt + ",time = " + diff);
            return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, account));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new JwtAuthenticationResponse(null, null));
        }

    }
}
