public class Developer extends Employee {
    private final double salary;

    public Developer(String n, double s) {
        super(n);
        salary = s;
    }

    public double monthlyPay() {
        return salary;
    }
}
