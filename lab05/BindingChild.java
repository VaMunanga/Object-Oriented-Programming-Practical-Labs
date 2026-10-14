public class BindingChild extends BindingParent {
    public String label = "child field";

    @Override
    public String describe() {
        return "child method";
    }

    public static String category() {
        return "child static";
    }

    public static void main(String[] a) {
        BindingParent p = new BindingChild();
        // EXPLAIN: overridden instance methods dispatch dynamically through the runtime object.
        System.out.println(p.describe());
        // EXPLAIN: fields and static methods bind to the reference/class named at compile time.
        System.out.println(p.label);
        System.out.println(BindingParent.category());
    }
}
