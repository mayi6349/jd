package com.nmtx.doc.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

public class DataUtils {

    private static final char[] STRING_SEED_POOL = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8',
            '9' };

    private static final char[] NUM_SEED_POOL = { '1', '2', '3', '4', '5', '6', '7', '8', '9' };

    private static final String[] BOOLEAN_SEED_POOL = { "true", "false" };

    public static String generateString(int length) {
        return RandomStringUtils.random(length, STRING_SEED_POOL);
    }

    public static String generateNumber(int length) {
        return RandomStringUtils.random(length, NUM_SEED_POOL);
    }
    
    public static String generateDouble() {
        Double data = RandomUtils.nextDouble(1, 1000);
        DecimalFormat    df   = new DecimalFormat("######0.00");   
        return df.format(data);
    }

    public static String generateBoolean() {
        Random random = new Random();
        int result = random.nextInt(1);
        return BOOLEAN_SEED_POOL[result];
    }
    
    public static String generateDate(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
}
