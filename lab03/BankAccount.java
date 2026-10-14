import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BankAccount {
    private final String accountNumber;
    private final String owner;
    private double balance;
    private final List<String> transactionHistory = new ArrayList<>();

    public BankAccount(String n, String o, double b) {
        accountNumber = text(n);
        owner = text(o);
        if (!Double.isFinite(b) || b < 0) throw new IllegalArgumentException("invalid opening balance");
        balance = b;
        transactionHistory.add("OPEN ZiG %.2f".formatted(b));
    }

    public void deposit(double a) {
        positive(a);
        balance += a;
        transactionHistory.add("DEPOSIT ZiG %.2f".formatted(a));
    }

    public boolean withdraw(double a) {
        positive(a);
        if (a > balance) return false;
        balance -= a;
        transactionHistory.add("WITHDRAW ZiG %.2f".formatted(a));
        return true;
    }

    public double getBalance() {
        return balance;
    }

    public List<String> getTransactionHistory() {
        return List.copyOf(transactionHistory);
    } // EXPLAIN: callers cannot mutate internal history.

    private static void positive(double a) {
        if (!Double.isFinite(a) || a <= 0) throw new IllegalArgumentException("amount must be positive");
    }

    private static String text(String s) {
        Objects.requireNonNull(s);
        if (s.isBlank()) throw new IllegalArgumentException("text required");
        return s.trim();
    }

    public static void main(String[] a) {
        var b = new BankAccount("HIT-001", "Carlton", 1000);
        b.deposit(500);
        b.withdraw(200);
        System.out.println(b.getTransactionHistory());
    }
}
