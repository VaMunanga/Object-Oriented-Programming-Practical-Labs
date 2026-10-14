public class Circle {
    private static int count;
    private final double radius;

    public Circle() {
        this(1.0); // EXPLAIN: this(...) delegates to the validating constructor and must be first.
    }

    public Circle(double radius) {
        if (!Double.isFinite(radius) || radius <= 0) throw new IllegalArgumentException("radius must be positive");
        this.radius = radius;
        count++;
    }

    public double getRadius() {
        return radius;
    }

    public double area() {
        return Math.PI * radius * radius;
    }

    public static int getCount() {
        return count;
    }

    public static void main(String[] args) {
        var unit = new Circle();
        var campus = new Circle(2.5);
        System.out.printf("Radii: %.1f, %.1f; count=%d%n", unit.radius, campus.radius, Circle.getCount());
    }
}
