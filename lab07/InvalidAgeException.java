public class InvalidAgeException extends Exception {
    public InvalidAgeException(int age) {
        super("age must be 0..120: " + age);
    }
}
