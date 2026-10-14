import java.util.ArrayList;
import java.util.List;

public class InitOrderDemo {
    private static final List<String> TRACE = new ArrayList<>();

    static { record("static block 1"); }
    static { record("static block 2"); }
    { record("instance block 1"); }
    { record("instance block 2"); }

    public InitOrderDemo() { record("constructor"); }

    private static void record(String event) {
        TRACE.add(event);
        System.out.println(event);
    }

    public static List<String> trace() { return List.copyOf(TRACE); }

    public static void main(String[] args) {
        System.out.println("--- first object ---");
        new InitOrderDemo();
        System.out.println("--- second object ---");
        new InitOrderDemo();
    }
}
