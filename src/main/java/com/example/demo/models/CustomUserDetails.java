package com.example.demo.models;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {
    private User user;
    

    public User getUser() {
        return user;
    }

    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(User user) {
        this.user=user;
        //this.authorities = user.getAuthorities(); 
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }
    
    public String getEmail() {
        return user.getEmail();
    }
    
    
}
