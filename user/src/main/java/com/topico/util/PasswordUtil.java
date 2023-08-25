package com.topico.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtil {
    private final BCryptPasswordEncoder passwordEncoder;


    public PasswordUtil(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public String hash(String inputPass) {
        return passwordEncoder.encode(inputPass);
    }

    public boolean isCorrect(String inputPass, String dbPassword) {
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
