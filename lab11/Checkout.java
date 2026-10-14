import java.util.Objects;

public class Checkout {
    private DiscountStrategy strategy;

    public Checkout(DiscountStrategy s) {
        strategy = Objects.requireNonNull(s);
    }

    public void setStrategy(DiscountStrategy s) {
        strategy = Objects.requireNonNull(s);
    }

    public double total(double p) {
        if (p < 0) throw new IllegalArgumentException();
        return strategy.apply(p);
    }

    public static void main(String[] a) {
        var c = new Checkout(p -> p);
        System.out.println(c.total(100));
        c.setStrategy(p -> p * .9);
        System.out.println(c.total(100));
    }
}
