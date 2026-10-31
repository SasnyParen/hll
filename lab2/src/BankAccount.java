public class BankAccount {
    private final String accountNumber;
    private double balance;

    public BankAccount(String accountNumber) {
        if (accountNumber == null || accountNumber.isBlank()) {
            throw new IllegalArgumentException("Номер счёта не должен быть пустым");
        }
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (!Double.isFinite(amount) || amount <= 0 || !Double.isFinite(balance + amount)) {
            throw new IllegalArgumentException("Некорректная сумма пополнения");
        }
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (!Double.isFinite(amount) || amount <= 0) {
            throw new IllegalArgumentException("Некорректная сумма снятия");
        }
        if (amount > balance) {
            return false;
        }
        balance -= amount;
        return true;
    }
}
