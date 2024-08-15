package com.example.demo.validations;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.example.demo.viewModels.UserRegistrationViewModel;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator  implements ConstraintValidator<PasswordMatches, UserRegistrationViewModel> {
    private static final Logger logger= LoggerFactory.getLogger(PasswordMatchesValidator.class);
    @Override
    public boolean isValid(UserRegistrationViewModel user, ConstraintValidatorContext context) {
        logger.info("Password matches: {}",user.getPassword().equals(user.getPasswordConfirm()));
        return user.getPassword().equals(user.getPasswordConfirm());
    }    
}
