package sg.edu.nus.iss.vttp5_ssf_day15workshop.service;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Repository;


@Repository
public class DataService {
    public DataService(){

    }

    public String getHexCode(){
        //creating random 8 digit hex string
        Random random = new Random();
        int randomInt = random.nextInt(00000000,99999999);
        String randomHex = Integer.toHexString(randomInt);

        return randomHex;
    }


    public boolean isAgeWithinRange(Date dateOfBirth){ //function for global error

        Date currentDate = Calendar.getInstance().getTime();
        Long millisecondsDiff = currentDate.getTime() - dateOfBirth.getTime();

        TimeUnit timeUnit = TimeUnit.DAYS;
        Long daysDiff = timeUnit.convert(millisecondsDiff, TimeUnit.MILLISECONDS);
        Long yearsDiff = daysDiff/365;
        System.out.println("\n\n\n\n" + daysDiff + "\n\n\n\n" + yearsDiff + "\n\n\n\n");
        if (yearsDiff < 10 || yearsDiff > 100 ){
            return false;
        }
        return true;
    }


}
