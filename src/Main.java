import auth.Auth;
import customer.Customer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static List<Customer> customers = new ArrayList<>();

    public static String MainMenu() {
        return """
                1- Customer
                2- Admin
                \n""";
    }

    public static String CustomerMenu() {
        return """
                1- Create Account
                2- Show Balance
                3- Deposit
                4- Withdraw
                5- Back to Main Menu
                6- Exit
                \n""";
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println(CustomerMenu());
            System.out.println("Please Enter Your Choice: ");

            int choice;

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.\n");
                continue;
            }

            switch (choice) {
                case 1:
                    Customer c = Auth.createAccount(scanner, customers);
                    customers.add(c);
                    System.out.println("Account created successfully!\n");
                    break;
                case 2:
                    if (customers.isEmpty()) {
                        System.out.println("No accounts found.\n");
                    } else {
                        Customer loggedInUser = Auth.login(scanner, customers);
                        if (loggedInUser != null) {
                            System.out.println(loggedInUser.getName() + " - Balance: " + loggedInUser.getBalance());
                        }
                    }
                    break;
                case 3:
                    if (customers.isEmpty()) {
                        System.out.println("No accounts found.\n");
                    } else {
                        try {
                            Customer loggedInUser = Auth.login(scanner, customers);
                            if (loggedInUser != null) {
                                System.out.print("Enter deposit amount: ");
                                double amount = Double.parseDouble(scanner.nextLine());
                                loggedInUser.deposit(amount);
                                System.out.println("Deposit successful! New balance: " + loggedInUser.getBalance());
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                case 4:
                    if (customers.isEmpty()) {
                        System.out.println("No accounts found.\n");
                    } else {
                        try {
                            Customer loggedInUser = Auth.login(scanner, customers);
                            if (loggedInUser != null) {
                                System.out.print("Enter Withdraw amount: ");
                                double amount = Double.parseDouble(scanner.nextLine());
                                loggedInUser.withdraw(amount);
                                System.out.println(
                                        "Withdrawal successful! Remaining balance: " + loggedInUser.getBalance());
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                case 5:
                    System.out.println("Goodbye!\n");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice.\n");
            }
        }
    }
}
