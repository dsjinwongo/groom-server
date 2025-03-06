package com.example.groom.common.auth.jwt;


import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;



@AllArgsConstructor
public class JwtAuthentication implements Authentication {
    Long userInfoId;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return userInfoId;
    }

    @Override
    public Object getDetails() {
        return userInfoId;
    }

    @Override
    public Long getPrincipal() {
        return userInfoId;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return null;
    }
}
