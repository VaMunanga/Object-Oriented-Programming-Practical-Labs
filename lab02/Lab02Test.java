import java.util.List;

public final class Lab02Test {
    private Lab02Test() {
    }

    public static void main(String[] args) {
        testCircleConstructorsAndCount();
        testEmployeeConstructorChainAndCopy();
        testInitializationOrder();
        testTemperatureFactories();
        System.out.println("Lab02Test: PASS");
    }

    private static void testCircleConstructorsAndCount() {
        int before = Circle.getCount();
        var unit = new Circle();
        var larger = new Circle(2.5);
        close(1.0, unit.getRadius(), "No-arg circle radius");
        close(2.5, larger.getRadius(), "Parameterized circle radius");
        check(Circle.getCount() == before + 2, "Circle counter must increment once per object");
        throwsType(IllegalArgumentException.class, () -> new Circle(0), "Zero radius rejected");
    }

    private static void testEmployeeConstructorChainAndCopy() {
        var defaultEmployee = new Employee();
        var namedEmployee = new Employee("Tadiwa");
        var founder = new Employee("Carlton", "Startup Founder", 18_150.00);
        var copy = new Employee(founder);
        check(defaultEmployee.getEmployeeId() != namedEmployee.getEmployeeId(), "IDs must be unique");
        check(copy.getEmployeeId() != founder.getEmployeeId(), "A copied employee needs a new identity");
        check(copy.getName().equals(founder.getName()), "Copy must retain employee values");
        close(18_150.00, copy.getMonthlySalaryZiG(), "Copy salary");
    }

    private static void testInitializationOrder() {
        List<String> initial = InitOrderDemo.trace();
        check(initial.equals(List.of("static block 1", "static block 2")), "Static blocks run once in source order");
        new InitOrderDemo();
        check(InitOrderDemo.trace().equals(List.of(
                "static block 1", "static block 2", "instance block 1", "instance block 2", "constructor")),
                "Instance blocks run before the constructor in source order");
    }

    private static void testTemperatureFactories() {
        var celsius = Temperature.fromCelsius(25);
        var fahrenheit = Temperature.fromFahrenheit(77);
        close(25.0, celsius.getCelsius(), "Celsius factory");
        close(celsius.getKelvin(), fahrenheit.getKelvin(), "Factories represent the same temperature");
        throwsType(IllegalArgumentException.class, () -> Temperature.fromCelsius(-274),
                "Temperature below absolute zero rejected");
    }

    private static void check(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }

    private static void close(double expected, double actual, String message) {
        if (Math.abs(expected - actual) > 0.000_001) {
            throw new AssertionError(message + ": expected " + expected + " but got " + actual);
        }
    }

    private static void throwsType(Class<? extends Throwable> type, Runnable action, String message) {
        try { action.run(); } catch (Throwable error) {
            if (type.isInstance(error)) return;
            throw new AssertionError(message + ": wrong exception", error);
        }
        throw new AssertionError(message + ": no exception");
    }
}
