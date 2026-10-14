public class Duck extends Animal {
    @Override
    public String sound() {
        return "Quack";
    }

    public static void main(String[] a) {
        Animal[] x = {new Cat(), new Cow(), new Duck()};
        // EXPLAIN: dynamic dispatch selects sound() from each object's runtime class.
        for (Animal v : x) System.out.println(v.sound());
    }
}
