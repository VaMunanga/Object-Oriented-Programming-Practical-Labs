import java.util.List;

public class FinallyDemo {
    public static int returnWithFinally(List<String> trace) {
        try {
            trace.add("try");
            return 21;
        } finally {
            // EXPLAIN: Java executes finally after evaluating return but before control reaches the caller.
            trace.add("finally");
            System.out.println("finally executes before return");
        }
    }

    public static void main(String[] a) {
        var t = new java.util.ArrayList<String>();
        System.out.println("returned " + returnWithFinally(t) + " " + t);
    }
}
