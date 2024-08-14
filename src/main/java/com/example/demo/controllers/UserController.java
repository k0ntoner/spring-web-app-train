package com.example.demo.controllers;

import java.security.Principal;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.CustomUserDetails;
import com.example.demo.models.User;
import com.example.demo.services.EmployeeService;
import com.example.demo.services.UserService;
import com.example.demo.viewModels.UserLoginViewModel;
import com.example.demo.viewModels.UserRegistrationViewModel;
import com.example.demo.viewModels.UserViewModel;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
   
    private static final Logger logger= LoggerFactory.getLogger(UserController.class);
    @GetMapping("/login")
    public String login( Model model) {
        CustomUserDetails userDetails= userService.getAuthenticatedUser();
        if(userDetails!=null){
            logger.info("User is already autorized, redirect to dashboard");
            
            return "redirect:/user/dashboard";
        }
        model.addAttribute("userLoginViewModel", new UserLoginViewModel());
        return "login";
    }
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("userLoginViewModel") UserLoginViewModel userLoginViewModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "login";
        }
        User user= userService.findByEmail(userLoginViewModel.getEmail());
        if(user!=null){
            Boolean result= userService.checkPassword(userLoginViewModel.getPassword(), user);
            if(result){
                userService.loadUserByUsername(user.getEmail());
            }
        }
        return null;
    }
    
    @GetMapping("/register")
    public String register(Model model) {
        CustomUserDetails userDetails= userService.getAuthenticatedUser();
        if(userDetails!=null){
            logger.info("User is already autorized, redirect to dashboard");
            
            return "redirect:/user/dashboard";
        }
        model.addAttribute("userRegistrationViewModel", new UserRegistrationViewModel());
        return "register";
    }
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("userRegistrationViewModel") UserRegistrationViewModel userRegistrationViewModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        User user= userService.findByEmail(userRegistrationViewModel.getEmail());
        
        if(user==null){
            user= new User("username", userRegistrationViewModel.getEmail(), userRegistrationViewModel.getPassword());
            User newUser=userService.createUser(user);
            if(newUser!=null){
                return "redirect:/user/login";
            }

        }
        bindingResult.rejectValue("email", "error.user", "An account already exists for this email.");
        return "register";
    }
    @GetMapping("/dashboard")
    public String dashboard(Principal principal, Model model) {
        User user=userService.findByEmail(principal.getName());
        UserViewModel userVM=new UserViewModel(user.getUsername(), user.getEmail() );
        model.addAttribute("userVM", userVM);
        return "dashboard";
    }
    
    
    
}
