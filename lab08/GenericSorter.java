import java.util.Arrays;

public class GenericSorter {
    public static <T extends Comparable<T>> void bubbleSort(T[] a) {
        boolean changed;
        do {
            changed = false;
            for (int i = 1; i < a.length; i++)
                if (a[i - 1].compareTo(a[i]) > 0) {
                    T t = a[i - 1];
                    a[i - 1] = a[i];
                    a[i] = t;
                    changed = true;
                }
        } while (changed);
    }

    public static void main(String[] a) {
        Integer[] i = {3, 1, 2};
        String[] s = {"ZiG", "Harare", "Code"};
        bubbleSort(i);
        bubbleSort(s);
        System.out.println(Arrays.toString(i));
        System.out.println(Arrays.toString(s));/* EXPLAIN: int is primitive and cannot replace erased reference type T; use Integer[]. */
    }
}
