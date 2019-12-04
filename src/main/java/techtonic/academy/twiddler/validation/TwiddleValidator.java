package techtonic.academy.twiddler.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import techtonic.academy.twiddler.entity.Twiddle;

@Component
public class TwiddleValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Twiddle.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        Twiddle twiddle = (Twiddle) object;

        if (twiddle.getContent().length() > 140) {
            errors.rejectValue("content","Length", "Twiddle cannot be more than 140 characters.");
        }
    }
}
