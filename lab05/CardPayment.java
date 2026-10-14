public class CardPayment implements Payment {
    private final String lastFour;
    private final double amount;

    public CardPayment(String n, double a) {
        if (n == null || n.isBlank() || a <= 0) throw new IllegalArgumentException();
        lastFour = n;
        amount = a;
    }

    public double amountZiG() {
        return amount;
    }

    public String process() {
        return "Card ****" + lastFour + " paid ZiG %.2f".formatted(amount);
    }
}
