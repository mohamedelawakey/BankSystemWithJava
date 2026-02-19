import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    /*
     * Customer -> Create Account, Show Balance, Deposit, Withdraw
     * Admin -> ManageCustomerAccounts
     */

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

    public static class Customer {
        private String name;
        private String mail;
        private String password;
        private int age;
        private double balance;

        public Customer() {

        }

        public Customer(String name, String mail, String password, int age, double balance) {
            setName(name);
            setMail(mail);
            setPassWord(password);
            setAge(age);
            setBalance(balance);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Name cannot be empty\n");
            }
            this.name = name;
        }

        public String getMail() {
            return mail;
        }

        public void setMail(String mail) {
            if (mail == null || mail.trim().isEmpty()) {
                throw new IllegalArgumentException("Mail cannot be empty\n");
            }

            if (!mail.contains("@") || !mail.contains(".")) {
                throw new IllegalArgumentException("Mail must be valid\n");
            }

            this.mail = mail;
        }

        public String getPassWord() {
            return password;
        }

        public void setPassWord(String password) {
            if (password == null || password.isEmpty()) {
                throw new IllegalArgumentException("Password cannot be empty\n");
            }

            if (password.length() < 8 || password.length() > 12) {
                throw new IllegalArgumentException("Password length must be between 8 and 12 characters\n");
            }

            int character = 0;
            int number = 0;
            int symbol = 0;

            for (char c : password.toCharArray()) {
                if (Character.isLetter(c)) {
                    character++;
                } else if (Character.isDigit(c)) {
                    number++;
                } else {
                    symbol++;
                }
            }

            if (character < 2 || number < 2 || symbol < 2) {
                throw new IllegalArgumentException(
                        "Password too weak. Must have at least 2 letters, 2 numbers, and 2 symbols");
            }

            this.password = password;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            if (age < 18 || age > 100) {
                throw new IllegalArgumentException("Age must be between 18 and 100\n");
            }

            this.age = age;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            if (balance < 0) {
                throw new IllegalArgumentException("Sorry balance must be more than zero\n");
            }

            this.balance = balance;
        }

        public void deposit(double amount) {
            if (amount < 500) {
                throw new IllegalArgumentException("Deposit amount must be at least 500\n");
            }

            this.balance += amount;
        }

        public void withdraw(double amount) {
            if (amount <= 0) {
                throw new IllegalArgumentException("Withdrawal amount must be positive\n");
            }

            if (amount > this.balance) {
                throw new IllegalArgumentException("Insufficient balance\n");
            }

            this.balance -= amount;
        }

        public static Customer createAccount() {
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
                } catch (Exception e) {
                    System.out.println("Invalid age. Try again.\n");
                }
            }

            while (true) {
                try {
                    System.out.print("Enter Balance: ");
                    customer.setBalance(Double.parseDouble(scanner.nextLine()));
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid balance. Try again.\n");
                }
            }

            return customer;
        }
    }

    public static Customer searchCustomer(String mail) {
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
                    Customer c = Customer.createAccount();
                    customers.add(c);
                    System.out.println("Account created successfully!\n");
                    break;
                case 2:
                    if (customers.isEmpty()) {
                        System.out.println("No accounts found.\n");
                    } else {
                        for (Customer cust : customers) {
                            System.out.println(cust.getName() + " - Balance: " + cust.getBalance());
                        }
                    }
                    break;
                case 3:
                    if (customers.isEmpty()) {
                        System.out.println("No accounts found.\n");
                    } else {
                        try {
                            System.out.print("Enter Your Email: ");
                            String mail = scanner.nextLine();

                            Customer customer = searchCustomer(mail);

                            if (customer == null) {
                                System.out.println("Customer not found.\n");
                                break;
                            }

                            System.out.print("Enter deposit amount: ");
                            double amount = Double.parseDouble(scanner.nextLine());
                            customer.deposit(amount);
                            System.out.println("Deposit successful! New balance: " + customer.getBalance());
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
                            System.out.print("Enter Your Email: ");
                            String mail = scanner.nextLine();

                            Customer customer = searchCustomer(mail);

                            if (customer == null) {
                                System.out.println("Customer not found.\n");
                                break;
                            }

                            System.out.print("Enter Withdraw amount: ");
                            Double amount = Double.parseDouble(scanner.nextLine());
                            customer.withdraw(amount);
                            System.out.println("Withdrawal successful! Remaining balance: " + customer.getBalance());
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
