import java.util.Objects;

public abstract class LibraryItem implements Borrowable {
    private final String id, title;
    private boolean available = true;

    protected LibraryItem(String i, String t) {
        id = text(i);
        title = text(t);
    }

    static String text(String s) {
        Objects.requireNonNull(s);
        if (s.isBlank() || s.contains(",") || s.contains("\n"))
            throw new IllegalArgumentException("nonblank CSV-safe text required");
        return s.trim();
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return available;
    }

    public abstract int loanPeriodDays();

    public void borrow(Member m) throws ItemNotAvailableException, MemberLimitExceededException {
        if (!available) throw new ItemNotAvailableException(id);
        m.ensureCanBorrow();
        available = false;
    }

    public void giveBack() {
        available = true;
    }

    void setAvailable(boolean a) {
        available = a;
    }

    public String toString() {
        return id + " " + title;
    }
}
