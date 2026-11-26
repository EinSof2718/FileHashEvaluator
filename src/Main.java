import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main {
    void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 1. Get the file path from the user
        System.out.print("Enter the full path to the file: ");
        String filePath = scanner.nextLine().trim();
        // 2. Get the desired hash algorithm
        System.out.print("Enter the algorithm (e.g., SHA-256, MD5, SHA-1): ");

        String algorithm = scanner.nextLine().trim();
        try
        {
          System.out.println("\nCalculating hash... Please wait.");
          String hashResult = FileHashEvaluator.calculateFileHash(filePath, algorithm);

          System.out.println("\n--- Results ---");
          System.out.println("File: " + filePath);
          System.out.println(algorithm.toUpperCase() + " Hash: " +hashResult);
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
        }//try-catch/finllly
   }//main
}
