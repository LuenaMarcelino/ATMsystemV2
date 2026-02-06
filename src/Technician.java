import java.util.Scanner;

public class Technician {

    private ATMStatus status;

    public Technician(ATMStatus status) {
        this.status = status;
    }

    public void showMenu(Scanner scanner, Bank bank) {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n╔════════════════════════════╗");
            System.out.println("║     TECHNICIAN MENU        ║");
            System.out.println("║                            ║");
            System.out.println("╚════════════════════════════╝");
            System.out.println("1. View ATM Status");
            System.out.println("2. View All Transaction History");
            System.out.println("3. Refill Banknotes");
            System.out.println("4. Refill Ink");
            System.out.println("5. Refill Paper");
            System.out.println("6. Upgrade Software");
            System.out.println("7. Upgrade Hardware");
            System.out.println("8. Logout");
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
                case 2 -> bank.getTransactionHistory().showAllHistory();
                case 3 -> {
                    System.out.println("\nSelect banknote to refill:");
                    System.out.println("1. €10");
                    System.out.println("2. €20");
                    System.out.println("3. €50");
                    System.out.println("4. €100");
                    System.out.print("Choice: ");
                    int noteChoice = Integer.parseInt(scanner.nextLine());

                    int noteValue = switch(noteChoice) {
                        case 1 -> 10;
                        case 2 -> 20;
                        case 3 -> 50;
                        case 4 -> 100;
                        default -> 0;
                    };

                    if (noteValue > 0) {
                        System.out.print("Enter quantity to add: ");
                        int quantity = Integer.parseInt(scanner.nextLine());
                        status.refillBanknotes(noteValue, quantity);
                    }
                }
                case 4 -> {
                    System.out.print("Enter ink amount to add (receipts): ");
                    int amount = Integer.parseInt(scanner.nextLine());
                    status.refillInk(amount);
                }
                case 5 -> {
                    System.out.print("Enter paper amount to add (receipts): ");
                    int amount = Integer.parseInt(scanner.nextLine());
                    status.refillPaper(amount);
                }
                case 6 -> status.upgradeSoftware();
                case 7 -> status.upgradeHardware();
                case 8 -> {
                    exit = true;
                    System.out.println("\nTechnician logged out");
                }
                default -> System.out.println("Invalid option");
            }
        }
    }
}

