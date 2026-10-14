import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.*;
import java.util.*;

public class LibraryCsvRepository {
    private final Path dir;

    public LibraryCsvRepository(Path d) {
        dir = d;
    }

    public void save(Library l) throws IOException {
        Files.createDirectories(dir);
        var is = new ArrayList<String>();
        for (var i : l.items())
            is.add(i instanceof Book b ? "BOOK,%s,%s,%s,%s".formatted(b.getId(), b.getTitle(), b.author(), b.isbn()) : "DVD,%s,%s,%d".formatted(i.getId(), i.getTitle(), ((Dvd) i).durationMinutes()));
        Files.write(dir.resolve("items.csv"), is, StandardCharsets.UTF_8);
        Files.write(dir.resolve("members.csv"), l.members().stream().map(m -> m.id() + "," + m.name()).toList(), StandardCharsets.UTF_8);
        Files.write(dir.resolve("loans.csv"), l.loans().stream().map(x -> String.join(",", x.memberId(), x.itemId(), x.borrowedOn().toString(), x.dueDate().toString(), x.returnedOn() == null ? "" : x.returnedOn().toString())).toList(), StandardCharsets.UTF_8);
    }

    public Library load() throws IOException {
        var l = new Library();
        for (String s : read("items.csv")) {
            String[] p = s.split(",", -1);
            l.addItem(p[0].equals("BOOK") ? new Book(p[1], p[2], p[3], p[4]) : new Dvd(p[1], p[2], Integer.parseInt(p[3])));
        }
        for (String s : read("members.csv")) {
            String[] p = s.split(",", -1);
            l.registerMember(new Member(p[0], p[1]));
        }
        for (String s : read("loans.csv")) {
            String[] p = s.split(",", -1);
            var x = new Loan(p[0], p[1], LocalDate.parse(p[2]), LocalDate.parse(p[3]));
            if (!p[4].isBlank()) x.restoreReturn(LocalDate.parse(p[4]));
            l.restoreLoan(x);
        }
        return l;
    }

    private List<String> read(String n) throws IOException {
        Path p = dir.resolve(n);
        return Files.exists(p) ? Files.readAllLines(p, StandardCharsets.UTF_8) : List.of();
    }
}
