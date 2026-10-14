import java.util.Comparator;

public record Student(String name, int marks) implements Comparable<Student> {
    public Student {
        if (name == null || name.isBlank() || marks < 0 || marks > 100) throw new IllegalArgumentException();
    }

    public static final Comparator<Student> BY_MARKS = Comparator.comparingInt(Student::marks);

    public int compareTo(Student o) {
        return name.compareToIgnoreCase(o.name);
    }
}
