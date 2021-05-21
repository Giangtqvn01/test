package com.example.actvn.security;

import com.example.actvn.entity.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserPrincipal implements UserDetails {

    private Integer id;
    @JsonIgnore
    private String taiKhoan;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private String hoVaTen;

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(Integer id, String hoVaTen, String taiKhoan, String password, List<GrantedAuthority> authorities) {
        this.id = id;
        this.taiKhoan = taiKhoan;
        this.hoVaTen = hoVaTen;
        this.password = password;
        this.authorities = authorities;
    }


    public static UserPrincipal create(Account user) {
        try {
            List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
                    new SimpleGrantedAuthority(role.getRoleCd().toString())
            ).collect(Collectors.toList());

            return new UserPrincipal(
                    user.getId(),
                    user.getHoVaTen(),
                    user.getTaiKhoan(),
                    user.getPassword(),
                    authorities
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public Integer getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
