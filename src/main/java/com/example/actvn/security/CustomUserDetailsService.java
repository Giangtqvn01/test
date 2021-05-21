package com.example.actvn.security;

import com.example.actvn.entity.Account;
import com.example.actvn.repository.AccountRepository;
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
    public UserDetails loadUserByUsername(String taiKhoan) throws UsernameNotFoundException {
        Account account = accountRepository.findByTaiKhoanAndHieuLuc(taiKhoan, Constant.ACTIVE_FLG.NOT_DELETE)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username : " + taiKhoan));
        UserDetails userDetails = UserPrincipal.create(account);
        return userDetails;
    }

    @Transactional
    public UserPrincipal loadUserById(Integer id) throws UsernameNotFoundException {
        Account account = accountRepository.findByIdAndHieuLuc(id, Constant.ACTIVE_FLG.NOT_DELETE)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id : " + id));
        return UserPrincipal.create(account);
    }
}
