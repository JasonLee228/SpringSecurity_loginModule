package com.spring.loginmodule.util.filter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Getter
@NoArgsConstructor
public class CustomUserDetails implements UserDetails {

    private Long id;
    private String email;
    private String username;
    private String role;

    private Map<String, Object> attributes;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }

    // 일반 로그인
    public CustomUserDetails(Long id, String email, String role) {
        this.id = id;
        this.email = email;
        this.role = role;
    }

    @Override
    public String getPassword() {
        return null;
    }

    // spring principalName 으로 사용
    @Override
    public String getUsername() {
        return username;
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
