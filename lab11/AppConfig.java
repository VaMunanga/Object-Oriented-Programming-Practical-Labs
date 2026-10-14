public final class AppConfig {
    // EXPLAIN: eager class initialisation creates exactly one shared Singleton instance.
    private static final AppConfig INSTANCE = new AppConfig();

    private AppConfig() {
    }

    public static AppConfig getInstance() {
        return INSTANCE;
    }

    public String institution() {
        return "Harare Institute of Technology";
    }

    public static void main(String[] a) {
        System.out.println(getInstance() == getInstance());
    }
}
