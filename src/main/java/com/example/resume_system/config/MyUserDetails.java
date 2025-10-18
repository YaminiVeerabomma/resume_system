package com.example.resume_system.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.resume_system.entity.User;

import java.util.Collection;
import java.util.Collections;

public class MyUserDetails implements UserDetails {

    private final User user;

    public MyUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Every user has ROLE_STUDENT by default
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_STUDENT"));
    }


    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail(); // email is used as username
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // account never expires
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // account is never locked
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // credentials never expire
    }

    @Override
    public boolean isEnabled() {
        return true; 
    }
}
