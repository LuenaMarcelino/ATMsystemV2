import java.util.Scanner;

public class Technician {

    private ATMStatus status;

    public Technician(ATMStatus status) {
        this.status = status;
    }

    public void showMenu(Scanner scanner) {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- TECHNICIAN MENU ---");
            System.out.println("1. View ATM Status");
            System.out.println("2. Replenish Cash");
            System.out.println("3. Replenish Paper");
            System.out.println("4. Upgrade Software");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid option");
                continue;
            }

            switch (choice) {
                case 1 -> status.showStatus();
                case 2 -> {
                    System.out.print("Enter cash amount to add: ");
                    double cash = Double.parseDouble(scanner.nextLine());
                    status.addCash(cash);
                }
                case 3 -> {
                    System.out.print("Enter paper amount to add: ");
                    int paper = Integer.parseInt(scanner.nextLine());
                    status.addPaper(paper);
                }
                case 4 -> status.upgradeSoftware();
                case 5 -> {
                    exit = true;
                    System.out.println("Technician logged out.");
                }
                default -> System.out.println("Invalid option");
            }
        }
    }
}