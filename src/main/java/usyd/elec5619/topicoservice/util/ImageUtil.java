package usyd.elec5619.topicoservice.util;

import org.springframework.web.multipart.MultipartFile;
import usyd.elec5619.topicoservice.exception.http.BadRequestException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ImageUtil {


    public static String getExt(MultipartFile imageFile) {
        String contentType = imageFile.getContentType();
        if (contentType == null || !contentType.startsWith("image/"))
            throw new BadRequestException("Invalid image type, content type must be image/*, got " + contentType);
        return contentType.split("/")[1];
    }

    public static String md5(byte[] input) {
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
