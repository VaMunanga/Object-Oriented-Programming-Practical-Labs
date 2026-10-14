public class CurrentAccount extends Account {
    public CurrentAccount(String o, double b) {
        super(o, b);
    }

    public double monthlyFee() {
        return 25;
    }
}
