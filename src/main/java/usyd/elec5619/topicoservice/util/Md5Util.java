package usyd.elec5619.topicoservice.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {

    public static String calculateMD5(byte[] input) {
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            //Perform the hashing
            byte[] hashBytes = md.digest(input);

            // Convert the byte array to a hexadecimal string
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            // Handle the exception
            throw new RuntimeException("Could not find MD5 algorithm", e);
        }
    }
}
