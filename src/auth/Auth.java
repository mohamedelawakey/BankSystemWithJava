package auth;

import customer.Customer;
import java.util.Scanner;
import java.util.List;

public class Auth {

    public static Customer createAccount(Scanner scanner, List<Customer> customers) {
        System.out.println("--- Create New Account ---\n");
        Customer customer = new Customer();

        while (true) {
            try {
                System.out.print("Enter Name: ");
                customer.setName(scanner.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage() + ". Try again.\n");
            }
        }

        while (true) {
            try {
                System.out.print("Enter Mail: ");
                customer.setMail(scanner.nextLine());

                if (searchCustomer(customer.getMail(), customers) != null) {
                    throw new IllegalArgumentException("Mail already exists\n");
                }

                break;
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage() + ". Try again.\n");
            }
        }

        while (true) {
            try {
                System.out.print("Enter Password: ");
                customer.setPassWord(scanner.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage() + ". Try again.\n");
            }
        }

        while (true) {
            try {
                System.out.print("Enter Age: ");
                customer.setAge(Integer.parseInt(scanner.nextLine()));
                break;
            } catch (NumberFormatException e) {
                System.err.println("Invalid input. Please enter a number.\n");
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage() + ". Try again.\n");
            }
        }

        while (true) {
            try {
                System.out.print("Enter Balance: ");
                customer.setBalance(Double.parseDouble(scanner.nextLine()));
                break;
            } catch (NumberFormatException e) {
                System.err.println("Invalid input. Please enter a number.\n");
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage() + ". Try again.\n");
            }
        }

        return customer;
    }

    public static Customer searchCustomer(String mail, List<Customer> customers) {
        if (mail == null || mail.trim().isEmpty()) {
            throw new IllegalArgumentException("Mail cannot be empty\n");
        }

        if (!mail.contains("@") || !mail.contains(".")) {
            throw new IllegalArgumentException("Mail must be valid\n");
        }

        for (Customer customer : customers) {
            if (customer.getMail().equals(mail)) {
                return customer;
            }
        }

        return null;
    }

    public static Customer login(Scanner scanner, List<Customer> customers) {
        while (true) {
            System.out.print("Enter Your Email: ");
            String mail = scanner.nextLine();
            Customer customer = searchCustomer(mail, customers);

            if (customer != null) {
                System.out.print("Enter Your Password: ");
                String password = scanner.nextLine();

                if (customer.getPassWord().equals(password)) {
                    System.out.println("Login successful!\n");
                    return customer;
                } else {
                    System.out.println("Incorrect password.\n");
                }
            } else {
                System.out.println("Customer not found.\n");
            }
            return null;
        }
    }
}
