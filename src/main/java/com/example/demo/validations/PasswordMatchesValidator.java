package com.example.demo.validations;



import com.example.demo.viewModels.UserRegistrationViewModel;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator  implements ConstraintValidator<PasswordMatches, UserRegistrationViewModel> {
    @Override
    public boolean isValid(UserRegistrationViewModel user, ConstraintValidatorContext context) {
        
        return user.getPassword().equals(user.getConfirmPassword());
    }    
}
