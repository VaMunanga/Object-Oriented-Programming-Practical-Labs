public final class Lab03Runner {
    private Lab03Runner() {
    }

    public static void main(String[] a) {
        System.out.println("=== Lab 03 - Exercise 1: Validated Student and Grade ===");
        Student.main(a);
        System.out.println("\n=== Lab 03 - Exercise 2: Protected Transaction History ===");
        BankAccount.main(a);
        System.out.println("\n=== Lab 03 - Exercise 3: Immutable Person ===");
        ImmutablePerson.main(a);
        System.out.println("\n=== Lab 03 - Exercise 4: Kelvin Encapsulation ===");
        Temperature.main(a);
    }
}
