import java.util.*;

public final class Lab06Test {
    public static void main(String[] a) {
        Shape[] s = {new Circle(2), new Rectangle(3, 4), new Square(5)};
        check(Math.abs(s[0].area() - Math.PI * 4) < 1e-6 && s[1].perimeter() == 14 && s[2].area() == 25, "shapes");
        check(new Bird("Chapungu").fly().contains("Chapungu") && new Aeroplane("Air Zimbabwe").fly().contains("Air Zimbabwe"), "flyable");
        Account x = new SavingsAccount("Tadiwa", 1000);
        x.deposit(500);
        check(x.balanceAfterMonthlyFee() == 1495, "savings fee");
        var students = new ArrayList<>(List.of(new Student("Tadiwa", 75), new Student("Carlton", 88), new Student("Leo", 65)));
        Collections.sort(students);
        check(students.get(0).name().equals("Carlton"), "natural name");
        students.sort(Student.BY_MARKS);
        check(students.get(0).name().equals("Leo"), "marks comparator");
        check(new HITGreeter().hello().equals("Hello | Mhoro"), "diamond");
        System.out.println("Lab06Test: PASS");
    }

    static void check(boolean c, String m) {
        if (!c) throw new AssertionError(m);
    }
}
