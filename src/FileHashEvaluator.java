import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class FileHashEvaluator
{
    public static String selectFileWithPopup() {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e)
        {
            // Fallback to default Java look if system look fails
        }
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a file to hash");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        System.out.println("Opening file chooser window... Check your taskbar if it doesn't pop up immediately.");
        int response = fileChooser.showOpenDialog(null);
        if (response == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = fileChooser.getSelectedFile();
            return selectedFile.getAbsolutePath();
        }//if
        return null;
    }//selectFileWithPopup

    public static String calculateFileHash(String filePath, String algorithm) throws NoSuchAlgorithmException, IOException {

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
}//FileHashEvaluator
