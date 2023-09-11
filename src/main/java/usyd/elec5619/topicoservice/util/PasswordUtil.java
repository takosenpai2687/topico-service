package usyd.elec5619.topicoservice.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtil {
    private final BCryptPasswordEncoder passwordEncoder;

    public PasswordUtil(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public Boolean isValid(String password) {
        final int N = password.length();
        return N >= 6 && N <= 16;
    }

    public Boolean isCorrect(String password, String hash) {
        return passwordEncoder.matches(password, hash);
    }

}
