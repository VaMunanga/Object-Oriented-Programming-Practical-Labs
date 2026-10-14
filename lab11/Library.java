import java.time.*;
import java.util.*;

public class Library {
    private final Map<String, LibraryItem> items = new HashMap<>();
    private final Map<String, Member> members = new HashMap<>();
    private final List<Loan> loans = new ArrayList<>();

    public void addItem(LibraryItem i) {
        if (items.putIfAbsent(i.getId(), i) != null) throw new IllegalArgumentException("duplicate item");
    }

    public void registerMember(Member m) {
        if (members.putIfAbsent(m.id(), m) != null) throw new IllegalArgumentException("duplicate member");
    }

    public LibraryItem findItem(String id) {
        var x = items.get(id);
        if (x == null) throw new IllegalArgumentException("unknown item: " + id);
        return x;
    }

    public Member findMember(String id) {
        var x = members.get(id);
        if (x == null) throw new IllegalArgumentException("unknown member: " + id);
        return x;
    }

    public Loan borrow(String m, String i) throws ItemNotAvailableException, MemberLimitExceededException {
        return borrow(m, i, LocalDate.now());
    }

    public Loan borrow(String m, String i, LocalDate on) throws ItemNotAvailableException, MemberLimitExceededException {
        var member = findMember(m);
        var item = findItem(i);
        item.borrow(member);
        var loan = new Loan(m, i, on, on.plusDays(item.loanPeriodDays()));
        member.add(i);
        loans.add(loan);
        return loan;
    }

    public void giveBack(String m, String i, LocalDate on) {
        var member = findMember(m);
        var item = findItem(i);
        var loan = loans.stream().filter(x -> x.memberId().equals(m) && x.itemId().equals(i) && x.isActive()).findFirst().orElseThrow(() -> new IllegalArgumentException("active loan not found"));
        loan.close(on);
        member.remove(i);
        item.giveBack();
    }

    public List<LibraryItem> searchByTitle(String q) {
        String n = q.toLowerCase();
        return items.values().stream().filter(i -> i.getTitle().toLowerCase().contains(n)).sorted(Comparator.comparing(LibraryItem::getTitle)).toList();
    }

    public List<Loan> overdueItems(LocalDate on) {
        return loans.stream().filter(l -> l.isOverdue(on)).sorted(Comparator.comparing(Loan::dueDate)).toList();
    }

    public List<LibraryItem> items() {
        return List.copyOf(items.values());
    }

    public List<Member> members() {
        return List.copyOf(members.values());
    }

    public List<Loan> loans() {
        return List.copyOf(loans);
    }

    void restoreLoan(Loan l) {
        loans.add(l);
        if (l.isActive()) {
            findItem(l.itemId()).setAvailable(false);
            findMember(l.memberId()).add(l.itemId());
        }
    }
}
