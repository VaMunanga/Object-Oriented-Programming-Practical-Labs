import java.util.*;

public class GradeBook {
    private final List<GradeListener> listeners = new ArrayList<>();

    public void subscribe(GradeListener l) {
        listeners.add(Objects.requireNonNull(l));
    }

    public void publish(String c, int m) {
        listeners.forEach(l -> l.onGradePublished(c, m));
    }

    public static void main(String[] a) {
        var g = new GradeBook();
        g.subscribe((c, m) -> System.out.println("Student portal: " + c + " = " + m));
        g.subscribe((c, m) -> {
            if (m < 50) System.out.println("Advisor alert");
        });
        g.publish("OOP", 72);
    }
}
