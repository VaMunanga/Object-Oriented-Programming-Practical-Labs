import java.nio.file.*;
import java.time.*;

public final class Lab11Runner {
    public static void main(String[] a) throws Exception {
        System.out.println("=== Lab 11 - Exercise 1: Singleton and Factory ===");
        AppConfig.main(a);
        NotificationFactory.main(a);
        System.out.println("\n=== Lab 11 - Exercise 2: Strategy and Observer ===");
        Checkout.main(a);
        GradeBook.main(a);
        System.out.println("\n=== Lab 11 - Exercise 3: Library End-to-End ===");
        var l = new Library();
        l.addItem(new Book("B1", "Java at HIT", "Mr Chiworera", "978-1"));
        l.addItem(new Dvd("D1", "Harare Startup Stories", 90));
        l.registerMember(new Member("M1", "Tadiwa"));
        l.borrow("M1", "B1", LocalDate.of(2026, 1, 1));
        System.out.println(l.searchByTitle("java"));
        System.out.println(l.overdueItems(LocalDate.of(2026, 2, 1)).size());
        l.giveBack("M1", "B1", LocalDate.of(2026, 1, 10));
        Path d = Files.createTempDirectory("lab11-run-");
        var r = new LibraryCsvRepository(d);
        r.save(l);
        System.out.println("Reloaded items: " + r.load().items().size());
    }
}
