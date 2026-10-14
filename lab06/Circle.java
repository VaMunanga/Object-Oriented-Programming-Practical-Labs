public class Circle extends Shape {
    private final double r;

    public Circle(double r) {
        this.r = positive(r);
    }

    public double area() {
        return Math.PI * r * r;
    }

    public double perimeter() {
        return 2 * Math.PI * r;
    }
}
