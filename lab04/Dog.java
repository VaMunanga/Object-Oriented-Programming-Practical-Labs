public class Dog extends Animal {public Dog(String name){super(name);} // EXPLAIN: without explicit super(name), Java tries missing Animal().
public String display(){return "Dog: "+name();}public static void main(String[]a){System.out.println(new Dog("Bingo").display());}
/* This deliberately stays commented so the lab compiles:
   Dog() { } // COMPILE ERROR: Animal has no no-argument constructor. */}
