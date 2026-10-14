public class Temperature {
    private final double kelvin;

    private Temperature(double kelvin) {
        if (!Double.isFinite(kelvin) || kelvin < 0)
            throw new IllegalArgumentException("temperature is below absolute zero");
        this.kelvin = kelvin;
    }

    public static Temperature fromCelsius(double celsius) {
        return new Temperature(celsius + 273.15);
    }

    public static Temperature fromFahrenheit(double fahrenheit) {
        return fromCelsius((fahrenheit - 32) * 5 / 9);
    }

    public double getKelvin() {
        return kelvin;
    }

    public double getCelsius() {
        return kelvin - 273.15;
    }

    public double getFahrenheit() {
        return getCelsius() * 9 / 5 + 32;
    }

    public static void main(String[] args) {
        // EXPLAIN: named factories state the input unit; overloaded double constructors could not.
        System.out.printf("Harare: %.1f C = %.1f F%n", fromCelsius(28).getCelsius(), fromCelsius(28).getFahrenheit());
    }
}
