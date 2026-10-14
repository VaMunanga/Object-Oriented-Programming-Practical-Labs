public class Car extends Vehicle {
    protected final String model;

    public Car(String make, String model) {
        super(make);
        this.model = model;
        trace.add("Car(" + model + ")");
        System.out.println(trace.get(trace.size() - 1));
    }
}
