public final class Lab09Runner {
    public static void main(String[] a) {
        System.out.println("=== Lab 09 - Exercise 1: Enums and Switch ===");
        for (var l : Level.values()) System.out.println(l + ": " + Level.advice(l));
        System.out.println("\n=== Lab 09 - Exercise 2: Records ===");
        StudentRecord.main(a);
        System.out.println("\n=== Lab 09 - Exercise 3: equals and hashCode ===");
        Book.main(a);
        System.out.println("\n=== Lab 09 - Exercise 4: Iterable Linked List ===");
        CustomLinkedList.main(a);
        System.out.println("\n=== Lab 09 - Exercise 5: Greedy Coins ===");
        Coin.main(a);
    }
}
