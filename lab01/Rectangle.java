import java.util.Objects;

public class Rectangle {
    // EXPLAIN: package access is deliberate in this introductory lab so direct alias mutation can be observed.
    double width;
    final double height;

    public Rectangle(double width, double height) {
        this.width = requirePositive(width, "width");
        this.height = requirePositive(height, "height");
    }

    public double area() {
        return width * height;
    }

    public double perimeter() {
        return 2 * (width + height);
    }

    public static Rectangle largestByArea(Rectangle[] rectangles) {
        Objects.requireNonNull(rectangles, "rectangles cannot be null");
        if (rectangles.length == 0) {
            throw new IllegalArgumentException("rectangles cannot be empty");
        }
        Rectangle largest = Objects.requireNonNull(rectangles[0], "rectangle cannot be null");
        for (int i = 1; i < rectangles.length; i++) {
            Rectangle candidate = Objects.requireNonNull(rectangles[i], "rectangle cannot be null");
            if (candidate.area() > largest.area()) {
                largest = candidate;
            }
        }
        return largest;
    }

    private static double requirePositive(double value, String field) {
        if (!Double.isFinite(value) || value <= 0) {
            throw new IllegalArgumentException(field + " must be a positive finite value");
        }
        return value;
    }

    @Override
    public String toString() {
        return "Rectangle[width=" + width + ", height=" + height + ", area=" + area() + "]";
    }

    public static void main(String[] args) {
        var rectangles = new Rectangle[]{
                new Rectangle(3, 4), new Rectangle(5, 6), new Rectangle(2, 8),
                new Rectangle(7, 5), new Rectangle(4, 4)
        };
        System.out.println("Largest: " + largestByArea(rectangles));

        Rectangle r1 = new Rectangle(4, 3);
        Rectangle r2 = r1;
        // EXPLAIN: r2 receives the same heap reference; it is not a copy of the Rectangle.
        r2.width = 10;
        System.out.println("r1.width after changing r2.width: " + r1.width);
    }
}
