public record Learner(String name, int roll, double marks) {
    public Learner {
        if (name == null || name.isBlank() || name.contains(",") || roll <= 0 || marks < 0 || marks > 100)
            throw new IllegalArgumentException();
    }

    public String toCsv() {
        return name + "," + roll + "," + marks;
    }

    public static Learner fromCsv(String s) {
        String[] p = s.split(",", -1);
        if (p.length != 3) throw new IllegalArgumentException("malformed CSV");
        return new Learner(p[0], Integer.parseInt(p[1]), Double.parseDouble(p[2]));
    }
}
