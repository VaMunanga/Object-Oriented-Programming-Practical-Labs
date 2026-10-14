public final class AppConfig {
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
