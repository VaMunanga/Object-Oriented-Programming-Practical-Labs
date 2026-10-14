public class SavingsAccount extends Account {
    public SavingsAccount(String o, double b) {
        super(o, b);
    }

    public double monthlyFee() {
        return 5;
    }
}
