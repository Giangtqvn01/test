package com.example.actvn.security;

import com.example.actvn.entity.Account;
import com.example.actvn.repository.account.AccountRepository;
import com.example.actvn.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        Account user = accountRepository.findByTaiKhoanAndHieuLuc(loginId, Constant.ACTIVE_FLG.NOT_DELETE)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email : " + loginId)
                );

        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(int id) {
        Account user = accountRepository.findByIdAndHieuLuc(id, Constant.ACTIVE_FLG.NOT_DELETE).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id : " + id)
        );
        return UserPrincipal.create(user);
    }
}
