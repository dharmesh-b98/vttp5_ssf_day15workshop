package sg.edu.nus.iss.vttp5_ssf_day15workshop.customvalidator;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AgeLimitValidator implements ConstraintValidator<AgeLimit, Date> {
    int minimumAge;
    int maximumAge;


    @Override
    public void initialize(AgeLimit constraintAnnotation) {
        this.minimumAge = constraintAnnotation.minimumAge();
        this.maximumAge = constraintAnnotation.maximumAge();
    }

    @Override
    public boolean isValid(Date dateOfBirth, ConstraintValidatorContext context) {
        if(dateOfBirth == null){
            return false;
        }
        Date currentDate = Calendar.getInstance().getTime();
        Long millisecondsDiff = currentDate.getTime() - dateOfBirth.getTime();

        TimeUnit timeUnit = TimeUnit.DAYS;
        Long daysDiff = timeUnit.convert(millisecondsDiff, TimeUnit.MILLISECONDS);
        Long yearsDiff = daysDiff/365;
        //System.out.println("\n\n\n\n" + daysDiff + "\n\n\n\n" + yearsDiff + "\n\n\n\n");
        if (yearsDiff < this.minimumAge || yearsDiff > this.maximumAge ){
            return false;
        }
        return true;
    }
    
}
