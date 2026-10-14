import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public class FileTextDemo {
    public static List<String> readFiveLines(Scanner scanner) {
        var lines = new ArrayList<String>(5);
        for (int i = 0; i < 5; i++) {
            if (!scanner.hasNextLine()) throw new IllegalArgumentException("exactly five lines are required");
            lines.add(scanner.nextLine());
        }
        return List.copyOf(lines);
    }

    public static void writeLines(Path p, List<String> l) throws IOException {
        Files.write(p, l, StandardCharsets.UTF_8);
    }

    public static List<String> readLines(Path p) throws IOException {
        return Files.readAllLines(p, StandardCharsets.UTF_8);
    }

    public static FileStats stats(Path p) throws IOException {
        String text = Files.readString(p, StandardCharsets.UTF_8);
        long words = Arrays.stream(text.split("\\s+")).filter(x -> !x.isBlank()).count();
        return new FileStats(text.lines().count(), words, text.length());
    }

    public static void main(String[] a) throws Exception {
        Path p = Path.of("five-lines.txt");
        var l = System.console() == null
                ? List.of("HIT", "Harare", "Carlton", "Tadiwa", "Leo")
                : readFiveLines(new Scanner(System.in));
        writeLines(p, l);
        System.out.println(readLines(p));
        System.out.println(stats(p));
        Files.deleteIfExists(p);
    }
}
