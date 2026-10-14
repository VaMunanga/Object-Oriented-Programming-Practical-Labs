import java.time.*;

public class Loan {
    private final String memberId, itemId;
    private final LocalDate borrowedOn, dueDate;
    private LocalDate returnedOn;

    public Loan(String m, String i, LocalDate b, LocalDate d) {
        memberId = m;
        itemId = i;
        borrowedOn = b;
        dueDate = d;
    }

    public String memberId() {
        return memberId;
    }

    public String itemId() {
        return itemId;
    }

    public LocalDate borrowedOn() {
        return borrowedOn;
    }

    public LocalDate dueDate() {
        return dueDate;
    }

    public LocalDate returnedOn() {
        return returnedOn;
    }

    public boolean isActive() {
        return returnedOn == null;
    }

    public boolean isOverdue(LocalDate on) {
        return isActive() && dueDate.isBefore(on);
    }

    void close(LocalDate on) {
        if (on.isBefore(borrowedOn)) throw new IllegalArgumentException();
        returnedOn = on;
    }

    void restoreReturn(LocalDate on) {
        returnedOn = on;
    }

    @Override
    public String toString() {
        return memberId + " -> " + itemId + " due " + dueDate + (returnedOn == null ? "" : " returned " + returnedOn);
    }
}
