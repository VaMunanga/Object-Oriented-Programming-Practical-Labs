import java.nio.file.*;
import java.time.*;

public final class Lab11Test {
    public static void main(String[] a) throws Exception {
        check(AppConfig.getInstance() == AppConfig.getInstance(), "singleton");
        check(NotificationFactory.create("sms").send("Hi").contains("SMS"), "factory");
        var c = new Checkout(p -> p);
        check(c.total(100) == 100, "strategy");
        c.setStrategy(p -> p * .9);
        check(c.total(100) == 90, "swap");
        var lib = new Library();
        lib.addItem(new Book("B1", "Java at HIT", "K. Chiworera", "978-1"));
        lib.addItem(new Dvd("D1", "Harare Startup Stories", 90));
        lib.registerMember(new Member("M1", "Tadiwa"));
        Loan loan = lib.borrow("M1", "B1", LocalDate.of(2026, 1, 1));
        check(!lib.findItem("B1").isAvailable() && loan.dueDate().equals(LocalDate.of(2026, 1, 15)), "borrow");
        try {
            lib.borrow("M1", "B1", LocalDate.now());
            throw new AssertionError("unavailable");
        } catch (ItemNotAvailableException expected) {
        }
        check(lib.searchByTitle("java").size() == 1 && lib.overdueItems(LocalDate.of(2026, 2, 1)).size() == 1, "streams");
        lib.giveBack("M1", "B1", LocalDate.of(2026, 1, 10));
        check(lib.findItem("B1").isAvailable(), "return");
        Path d = Files.createTempDirectory("library-");
        var repo = new LibraryCsvRepository(d);
        repo.save(lib);
        var loaded = repo.load();
        check(loaded.searchByTitle("startup").size() == 1 && loaded.findMember("M1").name().equals("Tadiwa"), "persistence");
        System.out.println("Lab11Test: PASS");
    }

    static void check(boolean c, String m) {
        if (!c) throw new AssertionError(m);
    }
}
