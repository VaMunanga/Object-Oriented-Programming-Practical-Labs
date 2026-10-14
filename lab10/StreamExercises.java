import java.util.*;
import java.util.function.Predicate;

public class StreamExercises {
    public static void removeEvens(List<Integer> x) {
        Predicate<Integer> even = n -> n % 2 == 0;
        x.removeIf(even);
    }

    public static List<String> aboveAverage(List<Student> s) {
        double avg = s.stream().mapToDouble(Student::marks).average().orElse(0);
        return s.stream().filter(x -> x.marks() > avg).map(Student::name).sorted().toList();
    }

    public static void main(String[] a) {
        var n = new ArrayList<>(List.of(1, 2, 3, 4));
        removeEvens(n);
        System.out.println(n);
        System.out.println(aboveAverage(List.of(new Student("Tadiwa", 70), new Student("Carlton", 90), new Student("Leo", 80))));
    }
}
