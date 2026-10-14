import java.util.List;
import java.util.Objects;

public final class ImmutablePerson {
    private final String name;
    private final int age;
    private final List<String> skills;

    public ImmutablePerson(String n, int a, List<String> s) {
        Objects.requireNonNull(n);
        if (n.isBlank() || a < 0) throw new IllegalArgumentException("invalid person");
        name = n.trim();
        age = a;
        skills = List.copyOf(Objects.requireNonNull(s));
    }

    public String name() {
        return name;
    }

    public int age() {
        return age;
    }

    public List<String> skills() {
        return skills;
    }

    public static void main(String[] a) {
        System.out.println(new ImmutablePerson("Tadiwa", 21, List.of("Java", "Business")).skills());
    }
}
