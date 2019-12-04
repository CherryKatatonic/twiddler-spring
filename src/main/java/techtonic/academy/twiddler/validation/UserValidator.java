package techtonic.academy.twiddler.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import techtonic.academy.twiddler.entity.User;

// Validates input from Signup form submission
@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {

        User user = (User) object;

        if (user.getPassword() == null) {
            errors.rejectValue("password", "Null", "Password can not be null");
        } else if (user.getPassword().length() < 6){
            errors.rejectValue("password","Length", "Password must be at least 6 characters");
        }

        if (user.getConfirmPassword() == null) {
            errors.rejectValue("confirmPassword", "Null", "Password can not be null");
        } else if (!user.getPassword().equals(user.getConfirmPassword())){
            errors.rejectValue("confirmPassword","Match", "Passwords must match");
        }
    }
}
