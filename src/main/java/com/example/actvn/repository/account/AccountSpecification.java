package com.example.actvn.repository.account;


import com.example.actvn.entity.User;
import com.example.actvn.model.account.GetAccountRequest;
import org.springframework.data.jpa.domain.Specification;

public class AccountSpecification {
    public static Specification<User> searchAccount(GetAccountRequest request){
        return null;
    }
}
