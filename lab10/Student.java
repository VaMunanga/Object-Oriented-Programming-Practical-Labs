public record Student(String name, double marks) {
    public Student {
        if (name == null || name.isBlank() || marks < 0 || marks > 100) throw new IllegalArgumentException();
    }
}
