import java.util.Objects;

public class Person {
    private final String name;
    final Address address;

    public Person(String name, Address address) {
        Objects.requireNonNull(name, "name cannot be null");
        if (name.isBlank()) {
            throw new IllegalArgumentException("name cannot be blank");
        }
        this.name = name.trim();
        this.address = address;
    }

    public String cityOr(String fallback) {
        Objects.requireNonNull(fallback, "fallback cannot be null");
        return address == null ? fallback : address.city;
    }

    public static void main(String[] args) {
        var person = new Person("Carlton", new Address("Harare", "Zimbabwe"));
        System.out.println(person.name + " lives in " + person.address.city);

        var personWithoutAddress = new Person("Leo", null);
        // EXPLAIN: personWithoutAddress.address.city would throw NullPointerException; guard null first.
        System.out.println(personWithoutAddress.name + " city: " + personWithoutAddress.cityOr("not supplied"));
    }
}
