public record StudentRecord(String name, int age) {
    public StudentRecord {
        if (name == null || name.isBlank() || age < 0) throw new IllegalArgumentException();
    }

    public static void main(String[] a) {
        // EXPLAIN: a record supplies final components, accessors, equals, hashCode, and toString by value.
        var x = new StudentRecord("Tadiwa", 21);
        System.out.println(x);
        System.out.println(x.equals(new StudentRecord("Tadiwa", 21)));
        System.out.println(x.name());
    }
}
