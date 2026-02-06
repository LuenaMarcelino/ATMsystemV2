import java.util.Scanner;

public class LoginManager {

    private static final String TECH_USERNAME = "admin";
    private static final String TECH_PASSWORD = "admin123";

    private static String loggedInUsername = null;

    public static Role login(Scanner scanner, Bank bank) {

        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();
        loggedInUsername = username; // Store for later use

        // Technician login
        if (username.equals(TECH_USERNAME)) {
            System.out.print("Enter technician password: ");
            String password = scanner.nextLine().trim();

            if (password.equals(TECH_PASSWORD)) {
                return Role.TECHNICIAN;
            } else {
                return Role.NONE;
            }
        }

        // Customer login
        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();

        if (bank.authenticate(username, password)) {
            return Role.CUSTOMER;
        }

        return Role.NONE;
    }

    public static String getLoggedInUsername() {
        return loggedInUsername;
    }
}

