import java.util.ArrayList;
import java.util.List;

public final class Lab03Test {
    public static void main(String[] args) {
        var student = new Student("Tariro", 80);
        check(student.getGrade() == 'A', "80 is A");
        student.setMarks(70);
        check(student.getGrade() == 'B', "70 is B");
        student.setMarks(60);
        check(student.getGrade() == 'C', "60 is C");
        student.setMarks(59);
        check(student.getGrade() == 'F', "59 is F");
        throwsIt(IllegalArgumentException.class, () -> student.setMarks(101), "marks above 100");

        var account = new BankAccount("HIT-001", "Carlton", 1_000);
        account.deposit(500);
        check(account.withdraw(200), "funded withdrawal");
        check(!account.withdraw(2_000), "overdraft rejected");
        check(account.getTransactionHistory().size() == 3, "transactions recorded");
        throwsIt(UnsupportedOperationException.class, () -> account.getTransactionHistory().clear(), "history immutable");

        var skills = new ArrayList<>(List.of("Java", "Entrepreneurship"));
        var person = new ImmutablePerson("Tadiwa", 21, skills);
        skills.clear();
        check(person.skills().size() == 2, "constructor defensive copy");
        throwsIt(UnsupportedOperationException.class, () -> person.skills().add("SQL"), "getter immutable");

        close(0, new Temperature(273.15).getCelsius(), "kelvin to celsius");
        close(32, new Temperature(273.15).getFahrenheit(), "kelvin to fahrenheit");
        throwsIt(IllegalArgumentException.class, () -> new Temperature(-0.01), "below absolute zero");
        System.out.println("Lab03Test: PASS");
    }

    static void check(boolean c, String m) {
        if (!c) throw new AssertionError(m);
    }

    static void close(double e, double a, String m) {
        if (Math.abs(e - a) > 1e-6) throw new AssertionError(m);
    }

    static void throwsIt(Class<? extends Throwable> t, Runnable r, String m) {
        try {
            r.run();
        } catch (Throwable e) {
            if (t.isInstance(e)) return;
            throw new AssertionError(m, e);
        }
        throw new AssertionError(m);
    }
}
