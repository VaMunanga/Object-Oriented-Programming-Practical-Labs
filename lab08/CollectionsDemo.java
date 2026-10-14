import java.util.*;

public class CollectionsDemo {
    public static int sum(List<Integer> x) {
        return x.stream().mapToInt(Integer::intValue).sum();
    }

    public static int max(List<Integer> x) {
        return x.stream().mapToInt(Integer::intValue).max().orElseThrow();
    }

    public static <T> List<T> reversed(List<T> x) {
        var r = new ArrayList<>(x);
        Collections.reverse(r);
        return List.copyOf(r);
    }

    public static <T> List<T> distinct(List<T> x) {
        return List.copyOf(new LinkedHashSet<>(x));
    }

    public static void main(String[] a) {
        var n = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(sum(n) + " " + max(n) + " " + reversed(n));
        System.out.println(distinct(List.of("Tadiwa", "Leo", "Tadiwa")));
    }
}
