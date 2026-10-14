import java.util.List;

public final class Lab05Test {
    public static void main(String[] a) {
        check(Printer.print(2).equals("int: 2") && Printer.print(new int[]{1, 2}).equals("int[]: [1, 2]"), "overloads");
        check(List.of(new Cat(), new Cow(), new Duck()).stream().map(Animal::sound).toList().equals(List.of("Meow", "Moo", "Quack")), "dispatch");
        Employee[] e = {new Manager("Rudo", 1000, 200), new Developer("Leo", 900), new Intern("Tadiwa", 300)};
        check(java.util.Arrays.stream(e).mapToDouble(Employee::monthlyPay).sum() == 2400, "payroll");
        BindingParent p = new BindingChild();
        check(p.describe().equals("child method") && p.label.equals("parent field") && BindingParent.category().equals("parent static"), "binding");
        Payment card = new CardPayment("4111", 302.5);
        check(PaymentProcessor.legacyPay(card).equals(card.process()), "payment designs");
        check(new MobileMoneyPayment("EcoCash", "0772123456", 605).process().contains("EcoCash"), "mobile money");
        System.out.println("Lab05Test: PASS");
    }

    static void check(boolean c, String m) {
        if (!c) throw new AssertionError(m);
    }
}
