import java.time.Year;

public final class Lab01Test {
    private Lab01Test() {
    }

    public static void main(String[] args) {
        testCarAntiqueBoundary();
        testRectangleCalculationsAndLargestSelection();
        testRectangleReferenceAliasing();
        testPersonAddressCompositionAndNullSafety();
        System.out.println("Lab01Test: PASS");
    }

    private static void testCarAntiqueBoundary() {
        int currentYear = Year.now().getValue();
        var exactlyTwentyFive = new Car("Mazda", currentYear - 25, 180_000);
        var twentySixYearsOld = new Car("Toyota", currentYear - 26, 230_000);

        check(!exactlyTwentyFive.isAntique(), "A car aged exactly 25 years is not antique");
        check(twentySixYearsOld.isAntique(), "A car older than 25 years is antique");
        expectThrows(IllegalArgumentException.class,
                () -> new Car("Honda", currentYear + 1, 0),
                "Future model years must be rejected");
    }

    private static void testRectangleCalculationsAndLargestSelection() {
        var rectangles = new Rectangle[] {
                new Rectangle(2, 3),
                new Rectangle(4, 5),
                new Rectangle(8, 2),
                new Rectangle(6, 6),
                new Rectangle(3, 7)
        };

        checkClose(6.0, rectangles[0].area(), "Rectangle area");
        checkClose(10.0, rectangles[0].perimeter(), "Rectangle perimeter");
        check(Rectangle.largestByArea(rectangles) == rectangles[3],
                "Largest selection must return the original largest rectangle");
        expectThrows(IllegalArgumentException.class, () -> new Rectangle(0, 2),
                "Zero width must be rejected");
    }

    private static void testRectangleReferenceAliasing() {
        var r1 = new Rectangle(4, 5);
        Rectangle r2 = r1;
        r2.width = 9;

        checkClose(9.0, r1.width, "Aliases must observe the same object's changed width");
        check(r1 == r2, "Aliased variables must hold the same reference");
    }

    private static void testPersonAddressCompositionAndNullSafety() {
        var person = new Person("Tadiwa", new Address("Harare", "Zimbabwe"));
        check("Harare".equals(person.address.city), "Chained dot access must reach the city");
        check("Harare".equals(person.cityOr("Unknown")), "A present address must expose its city");

        var personWithoutAddress = new Person("Leo", null);
        check("Unknown".equals(personWithoutAddress.cityOr("Unknown")),
                "A null address must be handled safely");
    }

    private static void check(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }

    private static void checkClose(double expected, double actual, String message) {
        if (Math.abs(expected - actual) > 0.000_001) {
            throw new AssertionError(message + ": expected " + expected + " but got " + actual);
        }
    }

    private static void expectThrows(Class<? extends Throwable> type, Runnable action, String message) {
        try {
            action.run();
        } catch (Throwable error) {
            if (type.isInstance(error)) {
                return;
            }
            throw new AssertionError(message + ": wrong exception " + error, error);
        }
        throw new AssertionError(message + ": no exception was thrown");
    }
}
