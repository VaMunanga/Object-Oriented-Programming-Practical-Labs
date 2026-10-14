import java.time.Year;
import java.util.Objects;

public class Car {
    private final String brand;
    private final int year;
    private final double mileage;

    public Car(String brand, int year, double mileage) {
        this.brand = requireText(brand, "brand");
        int currentYear = Year.now().getValue();
        if (year < 1886 || year > currentYear) {
            throw new IllegalArgumentException("year must be between 1886 and " + currentYear);
        }
        if (!Double.isFinite(mileage) || mileage < 0) {
            throw new IllegalArgumentException("mileage must be a non-negative finite value");
        }
        this.year = year;
        this.mileage = mileage;
    }

    public String getBrand() {
        return brand;
    }

    public int getYear() {
        return year;
    }

    public double getMileage() {
        return mileage;
    }

    public void display() {
        System.out.printf("%s (%d), mileage: %,.0f km%n", brand, year, mileage);
    }

    public boolean isAntique() {
        return Year.now().getValue() - year > 25;
    }

    private static String requireText(String value, String field) {
        Objects.requireNonNull(value, field + " cannot be null");
        if (value.isBlank()) {
            throw new IllegalArgumentException(field + " cannot be blank");
        }
        return value.trim();
    }

    public static void main(String[] args) {
        // EXPLAIN: new allocates a distinct Car object on the heap and returns its reference.
        var car = new Car("Mazda Demio", 1998, 212_450);
        car.display();
        System.out.println("Antique? " + car.isAntique());
    }
}
