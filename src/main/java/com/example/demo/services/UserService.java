package com.example.demo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.models.CustomUserDetails;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.utils.HashingUtil;
import com.example.demo.utils.SaltUtil;

@Service
public class UserService implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;
    
    private static final Logger logger= LoggerFactory.getLogger(EmployeeService.class);
    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public Boolean checkPassword(String password, User user){
        String enteredHashPassword=HashingUtil.hashPassword(password);

        return enteredHashPassword.equals(user.getPassword());
    }
    public User createUser(User user){
        //here, password will be encoded by hash function 
        
        String hashPassword=HashingUtil.hashPassword(user.getPassword());
        user.setPassword(hashPassword);
        return userRepository.save(user);
    }
    @Override
    public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user= userRepository.findByEmail(email);
        if(user==null){
            logger.error("User not dound");
            throw new UsernameNotFoundException("User not dound");
        }
        
        return new CustomUserDetails(user);
    }
    public CustomUserDetails getAuthenticatedUser(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null && authentication.isAuthenticated() &&!(authentication.getPrincipal() instanceof String)){
            return (CustomUserDetails) authentication.getPrincipal();
        }
        
        return null;
    }
   
}
