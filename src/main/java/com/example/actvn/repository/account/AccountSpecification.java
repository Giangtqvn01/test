package com.example.actvn.repository.account;

import com.example.actvn.entity.Account;
import com.example.actvn.model.account.GetAccountRequest;
import org.springframework.data.jpa.domain.Specification;

public class AccountSpecification {
    public static Specification<Account> searchAccount(GetAccountRequest request){
        return null;
    }
}
