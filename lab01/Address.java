import java.util.Objects;

public class Address {
    // EXPLAIN: package-visible fields allow the manual's exact person.address.city expression in Lab 1.
    final String city;
    final String country;

    public Address(String city, String country) {
        this.city = requireText(city, "city");
        this.country = requireText(country, "country");
    }

    private static String requireText(String value, String field) {
        Objects.requireNonNull(value, field + " cannot be null");
        if (value.isBlank()) {
            throw new IllegalArgumentException(field + " cannot be blank");
        }
        return value.trim();
    }
}
