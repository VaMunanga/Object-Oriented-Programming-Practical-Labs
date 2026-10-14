public abstract class Employee {
    private final String name;

    protected Employee(String n) {
        if (n == null || n.isBlank()) throw new IllegalArgumentException();
        name = n;
    }

    public String name() {
        return name;
    }

    public abstract double monthlyPay();
}
