import java.util.HashMap;
import java.util.Scanner;
import java.util.Random;

public class Main {

    static HashMap<String, String> urlMap = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();
    static final String BASE_URL = "http://short.ly/";

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n🌐 URL Shortener developed by Monty");
            System.out.println("1. Shorten URL");
            System.out.println("2. Retrieve Original URL");
            System.out.println("3. View All URLs");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    shortenURL();
                    break;
                case 2:
                    retrieveURL();
                    break;
                case 3:
                    viewAllURLs();
                    break;
                case 4:
                    System.out.println("Exiting... Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 4);
    }

 
    public static String generateShortCode() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder code = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(characters.length());
            code.append(characters.charAt(index));
        }

        return code.toString();
    }

    public static void shortenURL() {
        System.out.print("Enter long URL: ");
        String longURL = scanner.nextLine();

        String shortCode = generateShortCode();
        urlMap.put(shortCode, longURL);

        System.out.println("Short URL: " + BASE_URL + shortCode);
    }

    public static void retrieveURL() {
        System.out.print("Enter short URL: ");
        String input = scanner.nextLine();

        String shortCode;

        if (input.startsWith(BASE_URL)) {
            shortCode = input.substring(BASE_URL.length());
        } else {
            shortCode = input;  
        }

        if (urlMap.containsKey(shortCode)) {
            System.out.println("Original URL: " + urlMap.get(shortCode));
        } else {
            System.out.println("URL not found.");
        }
    }

    public static void viewAllURLs() {
        if (urlMap.isEmpty()) {
            System.out.println("No URLs stored yet.");
        } else {
            System.out.println("\nStored URLs:");
            for (String key : urlMap.keySet()) {
                System.out.println(BASE_URL + key + " → " + urlMap.get(key));
            }
        }
    }
}

