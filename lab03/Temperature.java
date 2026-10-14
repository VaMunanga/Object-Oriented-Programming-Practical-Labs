public class Temperature {
    private final double kelvin;
    public Temperature(double kelvin){if(!Double.isFinite(kelvin)||kelvin<0)throw new IllegalArgumentException("kelvin cannot be below zero");this.kelvin=kelvin;}
    public double getCelsius(){return kelvin-273.15;} public double getFahrenheit(){return getCelsius()*9/5+32;}
    public static void main(String[]a){var t=new Temperature(301.15);System.out.printf("Harare: %.1f C / %.1f F%n",t.getCelsius(),t.getFahrenheit());}
}
