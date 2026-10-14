import java.util.Arrays;

public class Printer {
    public static String print(int v) {
        return "int: " + v;
    }

    public static String print(double v) {
        return "double: " + v;
    }

    public static String print(String v) {
        return "String: " + v;
    }

    public static String print(int[] v) {
        return "int[]: " + Arrays.toString(v);
    }

    public static void main(String[] a) {
        System.out.println(print(21));
        System.out.println(print(30.25));
        System.out.println(print("HIT"));
        System.out.println(print(new int[]{1, 2, 3}));
    }
}
