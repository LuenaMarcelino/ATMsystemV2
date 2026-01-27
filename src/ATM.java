import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank();
        ATMStatus atmStatus = new ATMStatus();

        System.out.println("Welcome to the ATM!");

        Role role = LoginManager.login(sc, bank);

        if (role == Role.TECHNICIAN) {
            System.out.println("Technician login successful!");
            Technician technician = new Technician(atmStatus);
            technician.showMenu(sc);
            return;
        }

        if (role == Role.CUSTOMER) {
            System.out.println("Customer login successful!");

            // Get username from LoginManager (no need to ask again)
            String username = LoginManager.getLoggedInUsername();

            boolean exit = false;
            while (!exit) {
                System.out.println("\n1. Check Balance");
                System.out.println("2. Withdraw Cash");
                System.out.println("3. Deposit Funds");
                System.out.println("4. Transfer Funds");
                System.out.println("5. Exit");
                System.out.print("Choose an option: ");

                int choice;
                try {
                    choice = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid option");
                    continue;
                }

                switch (choice) {
                    case 1:
                        System.out.println("Balance: " + bank.checkBalance(username));
                        break;
                    case 2:
                        System.out.print("Enter amount to withdraw: ");
                        double w = Double.parseDouble(sc.nextLine());
                        if (bank.withdraw(username, w)) {
                            System.out.println("Withdraw successful");
                        } else {
                            System.out.println("Insufficient balance");
                        }
                        break;
                    case 3:
                        System.out.print("Enter amount to deposit: ");
                        double d = Double.parseDouble(sc.nextLine());
                        bank.deposit(username, d);
                        System.out.println("Deposit successful");
                        break;
                    case 4:
                        System.out.print("Enter recipient username: ");
                        String toUser = sc.nextLine();
                        System.out.print("Enter amount to transfer: ");
                        double t = Double.parseDouble(sc.nextLine());
                        if (bank.transfer(username, toUser, t)) {
                            System.out.println("Transfer successful");
                        } else {
                            System.out.println("Transfer failed");
                        }
                        break;
                    case 5:
                        exit = true;
                        System.out.println("Thanks for using the ATM.");
                        break;
                    default:
                        System.out.println("Invalid option");
                }
            }
            return;
        }

        System.out.println("Authentication failed. Exiting.");
    }
}
