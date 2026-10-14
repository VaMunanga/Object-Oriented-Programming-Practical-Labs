import java.util.*;

public class CustomLinkedList<T> implements Iterable<T> {
    private static class Node<T> {
        final T value;
        Node<T> next;

        Node(T v) {
            value = v;
        }
    }

    private Node<T> head, tail;

    public void add(T v) {
        var n = new Node<>(v);
        if (head == null) head = n;
        else tail.next = n;
        tail = n;
    }

    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<T> {
        private Node<T> next = head;

        public boolean hasNext() {
            return next != null;
        }

        public T next() {
            if (next == null) throw new NoSuchElementException();
            T v = next.value;
            next = next.next;
            return v;
        }
    }

    public static void main(String[] a) {
        var l = new CustomLinkedList<String>();
        l.add("Harare");
        l.add("Bulawayo");
        for (var x : l) System.out.println(x);
    }
}
