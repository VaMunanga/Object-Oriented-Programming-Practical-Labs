public class PaymentProcessor {
    public static String legacyPay(Payment p) {
        if (p instanceof CardPayment c) return c.process();
        if (p instanceof MobileMoneyPayment m) return m.process();
        throw new IllegalArgumentException("unsupported payment");
    }

    public static String pay(Payment p) {
        return p.process();
    }

    public static void main(String[] a) {
        Payment p = new MobileMoneyPayment("EcoCash", "0772123456", 605);
        System.out.println(legacyPay(p));
        System.out.println(pay(p));
    }
}
