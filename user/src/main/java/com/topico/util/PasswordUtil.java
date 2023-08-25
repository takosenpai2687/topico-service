package com.topico.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


public class PasswordUtil {
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public static String hash(String inputPass) {
        return passwordEncoder.encode(inputPass);
    }

    public static boolean isCorrect(String inputPass, String dbPassword) {
        return passwordEncoder.encode(inputPass).equals(dbPassword);
    }

    public static boolean isValid(String password) {
        // Check length
        if (password.length() < 6 || password.length() > 16) return false;

        // Check for at least one number
        if (!password.matches(".*\\d.*")) return false;

        // Check for at least one uppercase letter
        return !password.equals(password.toLowerCase());
    }
}
