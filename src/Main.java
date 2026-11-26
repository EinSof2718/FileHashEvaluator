import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main {
    void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String filePath = FileHashEvaluator.selectFileWithPopup();
        if (filePath == null)
        {
            System.out.println("No file was selected. Exiting program.");
            scanner.close();
            return;
        }
        System.out.println("Selected file: " + filePath);

        System.out.print("Enter the algorithm (e.g., SHA-256, MD5, SHA-1): ");
        String algorithm = scanner.nextLine().trim();

        try {
            System.out.println("\nCalculating hash... Please wait.");
            String hashResult = FileHashEvaluator.calculateFileHash(filePath, algorithm);

            System.out.println("\n--- Results ---");
            System.out.println("File: " + filePath);
            System.out.println(algorithm.toUpperCase() + " Hash: " + hashResult);
        }
        catch (NoSuchAlgorithmException e)
        {
            System.err.println("Error: The algorithm '" + algorithm + "' is not supported.");
        }
        catch (IOException e)
        {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        finally
        {
            scanner.close();
        }
   }//main
}
