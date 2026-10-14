import java.util.*;

public class Stack<T> {
    private final List<T> x = new ArrayList<>();

    public void push(T v) {
        x.add(v);
    }

    public T pop() {
        if (x.isEmpty()) throw new NoSuchElementException();
        return x.remove(x.size() - 1);
    }

    public T peek() {
        if (x.isEmpty()) throw new NoSuchElementException();
        return x.get(x.size() - 1);
    }

    public boolean isEmpty() {
        return x.isEmpty();
    }

    public static void main(String[] a) {
        var s = new Stack<String>();
        s.push("Java");
        s.push("Business");
        System.out.println(s.pop());
    }
}
