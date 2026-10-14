import java.util.Objects;

public class Employee {
    private static int nextEmployeeId = 1000;
    private final int employeeId;
    private final String name;
    private final String role;
    private final double monthlySalaryZiG;

    public Employee() {
        this("New Employee");
    }

    public Employee(String name) {
        this(name, "Trainee", 6_050.00);
    }

    public Employee(String name, String role, double monthlySalaryZiG) {
        this.employeeId = ++nextEmployeeId;
        this.name = text(name, "name");
        this.role = text(role, "role");
        if (!Double.isFinite(monthlySalaryZiG) || monthlySalaryZiG < 0) {
            throw new IllegalArgumentException("salary must be non-negative");
        }
        this.monthlySalaryZiG = monthlySalaryZiG;
    }

    public Employee(Employee other) {
        this(Objects.requireNonNull(other, "other cannot be null").name, other.role, other.monthlySalaryZiG);
        // EXPLAIN: this is a distinct heap object with copied values and a new employee identity.
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public double getMonthlySalaryZiG() {
        return monthlySalaryZiG;
    }

    private static String text(String value, String field) {
        Objects.requireNonNull(value, field + " cannot be null");
        if (value.isBlank()) throw new IllegalArgumentException(field + " cannot be blank");
        return value.trim();
    }

    @Override
    public String toString() {
        return "%d: %s, %s, ZiG %,.2f".formatted(employeeId, name, role, monthlySalaryZiG);
    }

    public static void main(String[] args) {
        var founder = new Employee("Carlton", "Startup Founder", 18_150);
        System.out.println(founder);
        System.out.println("Copied: " + new Employee(founder));
    }
}
