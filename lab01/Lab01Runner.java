public final class Lab01Runner {
    private Lab01Runner() {
    }

    public static void main(String[] args) {
        System.out.println("=== Lab 01 - Exercise 1: Cars ===");
        var cars = new Car[]{
                new Car("Toyota Corolla", 1997, 245_000),
                new Car("Honda Fit", 2018, 88_500),
                new Car("Mazda Demio", 2022, 34_100)
        };
        for (var car : cars) {
            car.display();
            System.out.println("Antique? " + car.isAntique());
        }

        System.out.println("\n=== Lab 01 - Exercise 2: Rectangle Array ===");
        var rectangles = new Rectangle[]{
                new Rectangle(3, 4), new Rectangle(5, 6), new Rectangle(2, 8),
                new Rectangle(7, 5), new Rectangle(4, 4)
        };
        System.out.println("Largest: " + Rectangle.largestByArea(rectangles));

        System.out.println("\n=== Lab 01 - Exercise 3: Reference Aliasing ===");
        Rectangle r1 = new Rectangle(4, 3);
        Rectangle r2 = r1;
        // EXPLAIN: assignment copies the reference, so both variables point to one heap object.
        r2.width = 10;
        System.out.println("r1.width after r2.width changed: " + r1.width);

        System.out.println("\n=== Lab 01 - Exercise 4: Composition ===");
        var student = new Person("Tadiwa", new Address("Harare", "Zimbabwe"));
        System.out.println("City via chained dots: " + student.address.city);

        System.out.println("\n=== Lab 01 - Exercise 5: Null Safety ===");
        var studentWithoutAddress = new Person("Leo", null);
        System.out.println("Safe city lookup: " + studentWithoutAddress.cityOr("address not supplied"));
    }
}
