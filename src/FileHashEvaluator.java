import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class FileHashEvaluator
{
    public static String calculateFileHash(String filePath, String algorithm) throws NoSuchAlgorithmException, IOException
    {

        MessageDigest digest = MessageDigest.getInstance(algorithm);
        byte[] buffer = new byte[8192]; // читаем файл блоками по 8 КБ
        int bytesRead;
        try (InputStream fis = new FileInputStream(filePath))
        {
            while ((bytesRead = fis.read(buffer)) != -1)
                digest.update(buffer, 0, bytesRead);
        }
        StringBuilder hex = new StringBuilder();
        for (byte b : digest.digest())
            hex.append(String.format("%02x", b));

        return hex.toString();
    }//calculateFileHash


    public static void copyToClipboard(String text)
    {
        StringSelection selection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, null);
    }//copyToClipboard
}//FileHashEvaluator
