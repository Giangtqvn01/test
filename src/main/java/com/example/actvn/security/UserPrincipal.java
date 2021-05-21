package com.example.actvn.security;



import com.example.actvn.entity.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserPrincipal implements UserDetails {
    private Integer accountId;

    private Integer drgStoreId;

    private String username;

    private String loginId;

    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(Integer accountId, String username, String loginId, String password, Collection<? extends GrantedAuthority> authorities) {
        this.accountId = accountId;
        this.username = username;
        this.loginId = loginId;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserPrincipal create(Account user) {
        List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getRoleCd().name())
        ).collect(Collectors.toList());

        return new UserPrincipal(
                user.getId(),
                user.getHoVaTen(),
                user.getTaiKhoan(),
                user.getPassword(),
                authorities
        );
    }

    public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Integer getDrgStoreId() {
		return drgStoreId;
	}

	public void setDrgStoreId(Integer drgStoreId) {
		this.drgStoreId = drgStoreId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(accountId, that.accountId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(accountId);
    }
}
