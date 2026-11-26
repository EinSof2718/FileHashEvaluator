import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class FileHashEvaluator
{
    public static String calculateFileHash(String filePath, String
            algorithm)
            throws NoSuchAlgorithmException, IOException {

        MessageDigest digest = MessageDigest.getInstance(algorithm); // [1]

        // Use a buffer so we don't load the entire file into memory atonce

        byte[] buffer = new byte[8192];
        int bytesRead;

        try (InputStream fis = new FileInputStream(filePath))
        {
            while ((bytesRead = fis.read(buffer)) != -1)
                digest.update(buffer, 0, bytesRead); // [1]

        }
        // Complete the hash computation
        byte[] hashBytes = digest.digest(); // [1]
        // Convert the byte array into a readable Hexadecimal string
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes)
        {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1)
                hexString.append('0');

            hexString.append(hex);
        }//for

        return hexString.toString();
    }//calculateFileHash
}//FileHashEvaluator
