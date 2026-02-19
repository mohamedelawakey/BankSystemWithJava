package customer;

public class Customer {
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

}
