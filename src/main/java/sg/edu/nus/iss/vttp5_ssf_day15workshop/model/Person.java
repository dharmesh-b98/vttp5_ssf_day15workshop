package sg.edu.nus.iss.vttp5_ssf_day15workshop.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.*;
import sg.edu.nus.iss.vttp5_ssf_day15workshop.customvalidator.AgeLimit;

public class Person {

    @NotNull(message = "Required")
    @Size(min=3, max=64, message ="size between 3 and 64")
    private String name;

    @NotNull(message = "Required")
    @Email(message = "Needs to be email format")
    private String email;

    @NotNull(message = "Required")
    @Min(value=100000,  message = "Needs to be 6 digits")
    @Max(value=999999, message = "Needs to be 6 digits")
    private Integer phoneNumber;

    @AgeLimit(minimumAge = 10, maximumAge = 100, message = "Age has to be between 10 and 100 years old") //CUSTOM VALIDATOR
    @NotNull(message = "Required")
    @Past(message= "Needs to be past date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")  //IMPORTANT this has to be this way or there will be problems with getting date info from form
    private Date dateOfBirth;

    public Person(){
    }

    public Person(String name,String email,Integer phoneNumber,Date dateOfBirth) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return name + "," + email + "," + String.valueOf(phoneNumber) + "," + String.valueOf(dateOfBirth.getTime());
    }

    

    

    


}
