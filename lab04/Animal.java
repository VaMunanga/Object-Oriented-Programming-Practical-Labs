public class Animal {
    private final String name;

    public Animal(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException();
        this.name = name.trim();
    }

    protected String name() {
        return name;
    }
}
