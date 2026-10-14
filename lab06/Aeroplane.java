public class Aeroplane implements Flyable {
    private final String airline;

    public Aeroplane(String a) {
        airline = a;
    }

    public String fly() {
        return airline + " aeroplane flies with engines";
    }

    public static void main(String[] a) {
        Flyable[] f = {new Bird("Chapungu"), new Aeroplane("Air Zimbabwe")};
        for (var x : f) System.out.println(x.fly());
    }
}
