package com.example.demo.utils;

import java.security.SecureRandom;
import java.util.Base64;

public class SaltUtil {
    public static String generateSalt(){
        SecureRandom secureRandom= new SecureRandom();
        byte[] salt=new byte[16];
        secureRandom.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }
}
