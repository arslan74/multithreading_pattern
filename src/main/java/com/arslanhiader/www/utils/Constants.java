package com.arslanhiader.www.utils;
import java.util.Random;

public class Constants {

    public static final long BUFFER_SIZE = 2;
    public static final long RETENTION_PERIOD = 500;


    public static String uniqueNumberGenerator(){
        Random rand = new Random();
        // Generates a random number between 0x10 and 0x20
        int randomNum = rand.nextInt();
        // Random hex number in result
        String result = Integer.toHexString(randomNum);
        return result;
    }
}
