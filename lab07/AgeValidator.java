public class AgeValidator {
    public static void validateAge(int age) throws InvalidAgeException {
        if (age < 0 || age > 120) throw new InvalidAgeException(age);
    }

    public static void main(String[] a) {
        try {
            validateAge(121);
        } catch (InvalidAgeException e) {
            System.out.println(e.getMessage());
        }
    }
}
