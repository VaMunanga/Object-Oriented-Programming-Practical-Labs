import java.util.*;

public final class Lab08Test {
    public static void main(String[] a) {
        var n = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        check(CollectionsDemo.sum(n) == 55 && CollectionsDemo.max(n) == 10 && CollectionsDemo.reversed(n).get(0) == 10, "numbers");
        check(CollectionsDemo.distinct(List.of("Tadiwa", "Leo", "Tadiwa")).equals(List.of("Tadiwa", "Leo")), "distinct");
        var s = new Stack<Integer>();
        s.push(1);
        s.push(2);
        check(s.peek() == 2 && s.pop() == 2 && !s.isEmpty(), "stack");
        check(WordFrequency.count("Java java, business!").toString().equals("{java=2, business=1}"), "frequency");
        Integer[] i = {3, 1, 2};
        String[] w = {"ZiG", "Harare", "Code"};
        GenericSorter.bubbleSort(i);
        GenericSorter.bubbleSort(w);
        check(Arrays.equals(i, new Integer[]{1, 2, 3}) && Arrays.equals(w, new String[]{"Code", "Harare", "ZiG"}), "sort");
        System.out.println("Lab08Test: PASS");
    }

    static void check(boolean c, String m) {
        if (!c) throw new AssertionError(m);
    }
}
