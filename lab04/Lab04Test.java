import java.util.List;

public final class Lab04Test {
    public static void main(String[] a) {
        var s = new Student("Tariro", 21, "Computer Science");
        var t = new Teacher("Mr Chiworera", 40, "OOP");
        check(s.display().contains("Tariro, age 21") && s.display().contains("Computer Science"), "student reuses person display");
        check(t.display().contains("OOP"), "teacher subject");
        var e = new ElectricCar("BYD", "Atto 3", 420);
        check(e.constructionTrace().equals(List.of("Vehicle(BYD)", "Car(Atto 3)", "ElectricCar(420 km)")), "constructor order");
        check(new Dog("Bingo").display().equals("Dog: Bingo"), "explicit super initializes name");
        var stack = new SafeStack<String>();
        stack.push("Java");
        stack.push("Business");
        check(stack.pop().equals("Business") && stack.peek().equals("Java"), "LIFO");
        check(List.of(SafeStack.class.getMethods()).stream().noneMatch(m -> m.getName().equals("add") || m.getName().equals("remove")), "list methods hidden");
        throwsIt(java.util.NoSuchElementException.class, () -> new SafeStack<>().pop(), "underflow");
        System.out.println("Lab04Test: PASS");
    }

    static void check(boolean c, String m) {
        if (!c) throw new AssertionError(m);
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
