public class Teacher extends Person {
    private final String subject;

    public Teacher(String n, int a, String s) {
        super(n, a);
        if (s == null || s.isBlank()) throw new IllegalArgumentException();
        subject = s.trim();
    }

    @Override
    public String display() {
        return super.display() + ", subject: " + subject;
    }

    public static void main(String[] a) {
        System.out.println(new Teacher("Mr Chiworera", 40, "OOP").display());
    }
}
