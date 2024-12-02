import java.util.*;

public class Test {
    public static String getHexCode(){
        //creating random 8 digit hex string
        Random random = new Random();
        int randomInt = random.nextInt(00000000,99999999);
        String randomHex = Integer.toHexString(randomInt);
        return randomHex;
    }

    public static void main(String[] args) {
        System.out.println(Test.getHexCode());
    }
}

