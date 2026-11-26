import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main {
    void main(String[] args) {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e)
        {
            // Fallback to default Java style if system look fails
        }

        // 1. Выбор файла
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Step 1: Select a file to hash");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int fileResponse = fileChooser.showOpenDialog(null);
        if (fileResponse != JFileChooser.APPROVE_OPTION)
        {
            JOptionPane.showMessageDialog(null, "No file selected. Exiting program.","Cancelled", JOptionPane.WARNING_MESSAGE);
            return;
        }//if

        File selectedFile = fileChooser.getSelectedFile();

        // 2. Выбор алгоритма из выпадающего списка
        String[] algorithms = {"SHA-256", "MD5", "SHA-1", "SHA-512", "SHA-384"};
        String selectedAlgorithm = (String) JOptionPane.showInputDialog(null,"Step 2: Choose the hash algorithm to apply:","Select Algorithm",JOptionPane.QUESTION_MESSAGE,null,algorithms, algorithms[0]);

        if (selectedAlgorithm == null)
        {
            JOptionPane.showMessageDialog(null, "No algorithm chosen. Exiting program.","Cancelled", JOptionPane.WARNING_MESSAGE);
            return;
        }//if

        // 3. Вычисление и вывод
        try
        {
            String hashResult = FileHashEvaluator.calculateFileHash(selectedFile.getAbsolutePath(), selectedAlgorithm);
            String resultMessage = String.format("File: %s\n\nAlgorithm: %s\n\nHash Result%s:\n%s", selectedFile.getName(), selectedAlgorithm, "", hashResult);
            JOptionPane.showMessageDialog(null, resultMessage,"Hash Computation Complete", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (NoSuchAlgorithmException e)
        {
            JOptionPane.showMessageDialog(null, "The algorithm is not supported.","Error", JOptionPane.ERROR_MESSAGE);
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, "Error reading file: " + e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
        }
   }//main
}
