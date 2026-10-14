import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class LearnerRepository {
    private final Path file;

    public LearnerRepository(Path f) {
        file = Objects.requireNonNull(f);
    }

    public void save(List<Learner> x) throws IOException {
        Files.write(file, x.stream().map(Learner::toCsv).toList(), StandardCharsets.UTF_8);
    }

    public List<Learner> load() throws IOException {
        if (!Files.exists(file)) return List.of();
        try (var s = Files.lines(file, StandardCharsets.UTF_8)) {
            return s.filter(x -> !x.isBlank()).map(Learner::fromCsv).toList();
        }
    }

    public Map<GradeBand, List<Learner>> groupByBand(List<Learner> x) {
        return x.stream().collect(Collectors.groupingBy(l -> GradeBand.of(l.marks()), () -> new EnumMap<>(GradeBand.class), Collectors.toList()));
    }

    public void writeReports(Path d, Map<GradeBand, List<Learner>> g) throws IOException {
        for (var b : GradeBand.values())
            Files.write(d.resolve("band-" + b + ".txt"), g.getOrDefault(b, List.of()).stream().map(Learner::toCsv).toList(), StandardCharsets.UTF_8);
    }
}
