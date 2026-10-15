# Object-Oriented Programming Practical Labs

Java exercises for the Object-Oriented Programming course at Harare Institute of Technology. The repository contains eleven practical labs, from classes and objects through a library management mini-project. The source lab manual, credited to **@Kenneth** and dated 28 September 2026, is preserved below.

- **Student:** Takudzwa M Munanga
- **Registration number:** H250363V
- **Course code:** ICS2101

## Repository guide

| Folder | Topic |
| --- | --- |
| `lab01` | Classes and objects |
| `lab02` | Constructors, `this`, and `static` |
| `lab03` | Encapsulation |
| `lab04` | Inheritance and composition |
| `lab05` | Polymorphism |
| `lab06` | Abstract classes and interfaces |
| `lab07` | Exception handling |
| `lab08` | Generics and collections |
| `lab09` | Nested types, enums, and records |
| `lab10` | File handling, lambdas, and streams |
| `lab11` | Design patterns and library management |

Each lab contains separate Java source files, a `LabXXRunner` demonstration, and a `LabXXTest` verification program. `lab11/README.md` includes the mini-project report and class diagram. `docs/superpowers/` records the implementation design and plan.

## Build and run

Install JDK 17 or later. The code was verified with Java 21 using `--release 17`. No external dependencies or build tool are needed. Compile one lab at a time because different folders reuse class names in the default package. From the repository root in PowerShell:

```powershell
$lab = 'lab01'  # change to lab02 ... lab11
New-Item -ItemType Directory -Force ".build/$lab" | Out-Null
$sources = Get-ChildItem $lab -Filter *.java | Select-Object -ExpandProperty FullName
javac --release 17 -d ".build/$lab" $sources
java -cp ".build/$lab" "Lab$($lab.Substring(3))Test"
java -cp ".build/$lab" "Lab$($lab.Substring(3))Runner"
```

For the interactive library in Lab 11, run `java -cp .build/lab11 LibraryConsole`. It loads and saves CSV files in `library-data/`; an optional first argument selects another data directory. The console menu expects one input value per line. Lab 7 scanner input and Lab 10 five-line file input are also covered by their tests; `FileTextDemo` uses sample lines when no interactive terminal is attached.

These labs give a progression from modeling simple objects to applying the four OOP pillars, collections, exceptions, file I/O, and design patterns in a small working application. The exercises and expected outputs in the manual below support independent practice and oral explanation.

## Lab manual

---

## Introduction and Lab Setup

This manual takes a class through eleven labs covering the core of object-oriented programming (OOP) in Java; each lab states its objectives, explains the theory, gives annotated code with expected output, and ends with graded exercises.

### Learning outcomes for the whole course
By the end of the manual a student should be able to:
* Model real-world entities as classes and objects, and explain the difference between them.
* Apply the four pillars of OOP: encapsulation, inheritance, polymorphism and abstraction.
* Write robust programs using exceptions, generics, collections, lambdas and streams.
* Read and write files, and choose sensible design patterns for common problems.
* Combine all of the above in a small but complete mini-project.

### Lab overview

| Lab | Topic | Main concepts | Suggested time |
| :--- | :--- | :--- | :--- |
| 1 | Classes and objects | Class, object, fields, methods, references, null | 2 hours |
| 2 | Constructors, `this`, `static` | Constructors, overloading, chaining, static members, init blocks | 2 hours |
| 3 | Encapsulation | Access modifiers, getters/setters, validation | 2 hours |
| 4 | Inheritance | `extends`, `super`, `protected`, `final`, `Object` class | 2 hours |
| 5 | Polymorphism | Overloading, overriding, dynamic dispatch, casting | 2 hours |
| 6 | Abstraction | Abstract classes, interfaces, default methods | 3 hours |
| 7 | Exception handling | `try`/`catch`/`finally`, custom exceptions, `try`-with-resources | 2 hours |
| 8 | Generics and collections | Generic classes/methods, `List`, `Set`, `Map`, `Comparator` | 3 hours |
| 9 | Nested types, enums, records | Inner classes, `enum`, `record`, `equals`/`hashCode`/`toString` | 2 hours |
| 10 | File I/O, lambdas, streams | `java.nio.file`, functional interfaces, Stream API | 3 hours |
| 11 | Design patterns and mini-project | Singleton, Factory, Observer, Strategy, integration | 4 hours |

### Software requirements
* JDK 17 or later (an LTS release such as 21 is recommended). Records, pattern matching for `instanceof` and text blocks used here need Java 16 or newer.
* Any editor or IDE (IntelliJ IDEA, Eclipse, VS Code with the Java extension), or just a text editor and a terminal.
* Check your installation:
```bash
java -version
javac -version
```

### Compiling and running
* Each `public` class lives in a file with exactly the same name, for example `Student.java`.
```bash
javac Student.java      # compiles to Student.class
java Student            # runs the main method
# Java 11+ shortcut for a single-file program:
java Student.java
```
* For programs with several files, keep them in one folder (or one package) and compile together: `javac *.java`.

### Conventions used in this manual
* Class names use `PascalCase`, methods and variables use `camelCase`, constants use `UPPER_SNAKE_CASE`.
* Comments beginning with `// EXPLAIN:` mark the line-by-line reasoning students should be able to repeat in a viva.
* Every lab has **Expected output**, so students can check their work before the instructor does.
* Exercises are graded in three levels: **Basic** (must complete), **Intermediate** (should complete), **Challenge** (optional extension).

### Suggested assessment scheme

| Component | Weight | What is assessed |
| :--- | :--- | :--- |
| Lab exercises (Labs 1 to 10) | 40% | Correctness, code style, comments |
| Viva / oral checks | 20% | Ability to explain each concept and trace code |
| Mini-project (Lab 11) | 30% | Design, use of OOP principles, testing |
| Lab record / report | 10% | Objectives, code, output screenshots, conclusions |

---

## Lab 1: Classes and Objects

### Objectives
* Define a class with fields (state) and methods (behaviour).
* Create objects with `new` and access members with the dot operator.
* Understand that object variables hold references, not the objects themselves.
* Recognise `null` and avoid `NullPointerException`.

### Theory
A **class** is a blueprint that describes what data an entity has and what it can do. An **object** is one concrete instance of that class, stored on the heap. Two objects of the same class have the same fields but usually different values in them.

A variable such as `Student s1` does not contain the student; it contains a **reference** (like an address) to the object. Assigning one reference variable to another copies the address, so both variables then point to the same object.

### Program 1.1: A Student class
File: `Student.java`
```java
public class Student {
    // EXPLAIN: instance variables define the STATE of every Student object
    String name;
    int rollNo;
    double gpa;

    // EXPLAIN: instance methods define BEHAVIOUR; they can read and change the fields
    void display() {
        System.out.println("Name: " + name + ", Roll No: " + rollNo + ", GPA: " + gpa);
    }

    boolean isHonours() {
        return gpa >= 3.5;   // EXPLAIN: returns true/false based on the object's own gpa
    }

    public static void main(String[] args) {
        // EXPLAIN: 'new' allocates memory on the heap and returns a reference
        Student s1 = new Student();
        s1.name = "Tariro";
        s1.rollNo = 101;
        s1.gpa = 3.8;

        Student s2 = new Student();
        s2.name = "Farai";
        s2.rollNo = 102;
        s2.gpa = 3.1;

        s1.display();
        s2.display();
        System.out.println(s1.name + " honours? " + s1.isHonours());
        System.out.println(s2.name + " honours? " + s2.isHonours());
    }
}
```
**Expected output**
```text
Name: Tariro, Roll No: 101, GPA: 3.8
Name: Farai, Roll No: 102, GPA: 3.1
Tariro honours? true
Farai honours? false
```

### Program 1.2: References, aliasing and null
File: `ReferenceDemo.java`
```java
public class ReferenceDemo {
    public static void main(String[] args) {
        Student a = new Student();
        a.name = "Original";

        // EXPLAIN: b copies the REFERENCE, not the object -> a and b are aliases
        Student b = a;
        b.name = "Changed via b";
        System.out.println(a.name);            // prints: Changed via b

        // EXPLAIN: == on references compares addresses, not contents
        Student c = new Student();
        c.name = "Changed via b";
        System.out.println(a == b);            // true  (same object)
        System.out.println(a == c);            // false (different objects)

        // EXPLAIN: a reference that points to nothing is null
        Student d = null;
        if (d == null) {
            System.out.println("d does not refer to any object");
        }
        // d.display();  // would throw NullPointerException at runtime
    }
}
```
**Expected output**
```text
Changed via b
true
false
d does not refer to any object
```

### Program 1.3: Objects that hold other objects
File: `Course.java`
```java
public class Course {
    String title;
    Student topStudent;   // EXPLAIN: a field can itself be a reference to another object

    public static void main(String[] args) {
        Student s = new Student();
        s.name = "Rudo";
        s.gpa = 3.9;

        Course c = new Course();
        c.title = "Object-Oriented Programming";
        c.topStudent = s;

        // EXPLAIN: chained dot operator: course -> student -> name
        System.out.println(c.title + " top student: " + c.topStudent.name);
    }
}
```

### Common mistakes
* Forgetting `new`, so the variable stays `null` and the first use throws `NullPointerException`.
* Comparing strings with `==` instead of `.equals()`.
* Assuming `Student b = a;` makes a copy of the student.

### Exercises
1. **Basic:** Create a `Car` class with fields `brand`, `year` and `mileage`, and a method `display()`. Create three cars and print them.
2. **Basic:** Add a method `isAntique()` to `Car` that returns `true` when the car is more than 25 years old.
3. **Intermediate:** Write a `Rectangle` class with `width`, `height`, and methods `area()` and `perimeter()`. Create an array of 5 rectangles and print the one with the largest area.
4. **Intermediate:** Predict, then run, a program that assigns `Rectangle r2 = r1;`, changes `r2.width`, and prints `r1.width`. Explain the result in your lab record.
5. **Challenge:** Create `Address` and `Person` classes where a `Person` has an `Address`. Print a person's city using a chained dot expression, and show what happens when the address is `null`.

---

## Lab 2: Constructors, the `this` Keyword and `static` Members

### Objectives
* Initialise objects safely using constructors.
* Overload constructors and chain them with `this(...)`.
* Use `this` to distinguish fields from parameters.
* Explain the difference between instance members and `static` members.
* Use `static` and instance initialiser blocks.

### Theory
A **constructor** is a special block that runs when an object is created. It has the same name as the class and no return type. If you write none, Java supplies a hidden no-argument (default) constructor; as soon as you write any constructor, the default one disappears.

`this` is a reference to the current object. It resolves name clashes (`this.name = name`) and lets one constructor call another (`this(...)`, which must be the first statement).

A `static` member belongs to the **class itself**, not to any object. There is exactly one copy, shared by all instances, and it can be reached without creating an object (`Book.getCount()`).

### Program 2.1: Constructors and `this`
File: `Book.java`
```java
public class Book {
    // EXPLAIN: static field -> ONE copy shared by all Book objects
    private static int count = 0;

    // instance fields -> each Book has its own copy
    private final int id;
    private String title;
    private String author;
    private double price;

    // EXPLAIN: static initialiser block runs once, when the class is first loaded
    static {
        System.out.println("Book class loaded");
    }

    // EXPLAIN: instance initialiser block runs before EVERY constructor body
    {
        count++;
        id = count;   // final field assigned exactly once
    }

    // Constructor 1: full initialisation
    public Book(String title, String author, double price) {
        this.title = title;       // EXPLAIN: this.title = field, title = parameter
        this.author = author;
        this.price = price;
    }

    // Constructor 2: constructor chaining with this(...)
    public Book(String title, String author) {
        this(title, author, 0.0);  // EXPLAIN: must be the FIRST statement
    }

    // Constructor 3: no-argument constructor
    public Book() {
        this("Untitled", "Unknown");
    }

    // EXPLAIN: static method -> no 'this'; it can only use static members directly
    public static int getCount() {
        return count;
    }

    public void display() {
        System.out.printf("#%d %s by %s ($%.2f)%n", id, title, author, price);
    }

    public static void main(String[] args) {
        Book b1 = new Book("Clean Code", "Robert Martin", 39.99);
        Book b2 = new Book("Effective Java", "Joshua Bloch");
        Book b3 = new Book();

        b1.display();
        b2.display();
        b3.display();
        System.out.println("Total books created: " + Book.getCount());
    }
}
```
**Expected output**
```text
Book class loaded
#1 Clean Code by Robert Martin ($39.99)
#2 Effective Java by Joshua Bloch ($0.00)
#3 Untitled by Unknown ($0.00)
Total books created: 3
```

### Program 2.2: Order of initialisation
File: `InitOrder.java`
```java
public class InitOrder {
    static int a = show("static field");
    int b = show("instance field");

    static { show("static block"); }
    { show("instance block"); }

    InitOrder() { show("constructor"); }

    static int show(String msg) {
        System.out.println(msg);
        return 1;
    }

    public static void main(String[] args) {
        System.out.println("--- first object ---");
        new InitOrder();
        System.out.println("--- second object ---");
        new InitOrder();
    }
}
```
**Expected output**
```text
static field
static block
--- first object ---
instance field
instance block
constructor
--- second object ---
instance field
instance block
constructor
```
Static parts run once when the class loads; instance parts run for every object, in the order written, before the constructor body.

### Program 2.3: A copy constructor
```java
public class Point {
    private final int x, y;

    public Point(int x, int y) { this.x = x; this.y = y; }

    // EXPLAIN: copy constructor creates an independent object with the same values
    public Point(Point other) { this(other.x, other.y); }

    public String toString() { return "(" + x + ", " + y + ")"; }

    public static void main(String[] args) {
        Point p1 = new Point(3, 4);
        Point p2 = new Point(p1);   // a true copy, not an alias
        System.out.println(p1 + " " + p2 + " same object? " + (p1 == p2));
    }
}
```

### Common mistakes
* Writing a return type (for example `void Book()`), which turns the constructor into an ordinary method.
* Calling `this(...)` after another statement; it must come first.
* Using `this` or instance fields inside a `static` method.
* Expecting the default constructor to remain after defining another constructor.

### Exercises
1. **Basic:** Create a `Circle` class with a constructor taking `radius`, and a no-argument constructor defaulting the radius to `1.0`.
2. **Basic:** Add a `static` counter to `Circle` that tracks how many circles exist, with a `static` getter.
3. **Intermediate:** Write an `Employee` class with an auto-incrementing `employeeId` (use a `static` counter), three overloaded constructors and a copy constructor.
4. **Intermediate:** Predict the output of a class containing two `static` blocks and two instance blocks, then verify it.
5. **Challenge:** Implement a `Temperature` class with static factory methods `fromCelsius(double)` and `fromFahrenheit(double)` and a `private` constructor. Explain why static factories can be clearer than overloaded constructors.

---

## Lab 3: Encapsulation and Access Modifiers

### Objectives
* Hide internal data with `private` fields and expose it through controlled methods.
* Apply the four access levels correctly.
* Enforce validation rules (class invariants) inside setters and constructors.
* Design classes that cannot be put into an invalid state.

### Theory
**Encapsulation** bundles data and the methods that operate on it into one unit and restricts direct outside access. The class becomes a black box: callers use its public methods and cannot corrupt its internals. Benefits are **safety** (validation), **flexibility** (internals can change without breaking callers) and **simpler reasoning**.

| Modifier | Same class | Same package | Subclass (other package) | Anywhere |
| :--- | :--- | :--- | :--- | :--- |
| `private` | Yes | No | No | No |
| *(default, no keyword)* | Yes | Yes | No | No |
| `protected` | Yes | Yes | Yes | No |
| `public` | Yes | Yes | Yes | Yes |

Rule of thumb: make fields `private`, make methods `public` only when they are part of the class's intended interface.

### Program 3.1: A safe BankAccount
File: `BankAccount.java`
```java
public class BankAccount {
    // EXPLAIN: private -> no code outside this class can touch these directly
    private final String accountNumber;
    private String ownerName;
    private double balance;

    public BankAccount(String accountNumber, String ownerName, double openingBalance) {
        if (openingBalance < 0) {
            throw new IllegalArgumentException("Opening balance cannot be negative");
        }
        this.accountNumber = accountNumber;
        setOwnerName(ownerName);          // reuse validation in the setter
        this.balance = openingBalance;
    }

    // Getters: read-only access
    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName()     { return ownerName; }
    public double getBalance()       { return balance; }

    // Setter with validation
    public void setOwnerName(String ownerName) {
        if (ownerName == null || ownerName.isBlank()) {
            throw new IllegalArgumentException("Owner name required");
        }
        this.ownerName = ownerName.trim();
    }

    // EXPLAIN: no setBalance() on purpose -> balance changes only via deposit/withdraw
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit must be positive");
        }
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal must be positive");
        }
        if (amount > balance) {
            return false;                  // insufficient funds, state unchanged
        }
        balance -= amount;
        return true;
    }

    @Override
    public String toString() {
        return String.format("%s [%s] balance: %.2f", accountNumber, ownerName, balance);
    }
}
```
File: `BankDemo.java`
```java
public class BankDemo {
    public static void main(String[] args) {
        BankAccount acc = new BankAccount("ACC-001", "Tendai", 500.00);
        acc.deposit(250.00);
        System.out.println(acc);

        System.out.println("Withdraw 100: " + acc.withdraw(100.00));
        System.out.println("Withdraw 5000: " + acc.withdraw(5000.00));
        System.out.println(acc);

        // acc.balance = 1_000_000;   // COMPILE ERROR: balance has private access

        try {
            acc.deposit(-50);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
    }
}
```
**Expected output**
```text
ACC-001 [Tendai] balance: 750.00
Withdraw 100: true
Withdraw 5000: false
ACC-001 [Tendai] balance: 650.00
Rejected: Deposit must be positive
```

### Program 3.2: The defensive-copy trap
Encapsulation can leak when a getter returns a reference to a mutable object.
File: `Team.java`
```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Team {
    private final List<String> members = new ArrayList<>();

    public void addMember(String name) { members.add(name); }

    // BAD: caller receives the real list and can modify it directly
    public List<String> getMembersUnsafe() { return members; }

    // GOOD: caller receives a read-only view
    public List<String> getMembers() { return Collections.unmodifiableList(members); }

    public static void main(String[] args) {
        Team t = new Team();
        t.addMember("Chipo");
        t.getMembersUnsafe().clear();                 // silently empties the team!
        System.out.println("After unsafe clear: " + t.getMembers().size());

        t.addMember("Chipo");
        try {
            t.getMembers().clear();                   // blocked
        } catch (UnsupportedOperationException e) {
            System.out.println("Safe getter blocked modification");
        }
    }
}
```
**Expected output**
```text
After unsafe clear: 0
Safe getter blocked modification
```

### Common mistakes
* Making every field `public` "for convenience".
* Generating a setter for every field automatically; only provide setters for values that should change.
* Returning references to internal mutable objects (lists, arrays, dates).
* Validating in the setter but forgetting to validate in the constructor.

### Exercises
1. **Basic:** Write a `Student` class with `private` `name` and `marks` (0 to 100). Reject invalid marks in the setter.
2. **Basic:** Add `getGrade()` that returns `A`, `B`, `C` or `F` from the marks, without storing the grade in a field.
3. **Intermediate:** Extend `BankAccount` with a transaction history (a list of strings). Provide a getter that returns an unmodifiable copy.
4. **Intermediate:** Create an `ImmutablePerson` class (`final` class, `final` fields, no setters). Explain each design choice.
5. **Challenge:** Build a `Temperature` class that stores `kelvin` internally but offers `getCelsius()` and `getFahrenheit()`. Reject values below absolute zero, and show that changing the internal unit would not break callers.

---

## Lab 4: Inheritance

### Objectives
* Create subclasses with `extends` and reuse superclass code.
* Call superclass constructors and methods with `super`.
* Use `protected`, `final` and the `@Override` annotation correctly.
* Explain the constructor call order and the role of `java.lang.Object`.
* Decide between inheritance ("is-a") and composition ("has-a").

### Theory
**Inheritance** lets a class (the **subclass**) acquire the fields and methods of another (the **superclass**) and add or change behaviour. Java supports single inheritance of classes: a class has exactly one direct superclass, and every class ultimately extends `Object`.

When a subclass object is created, the superclass constructor runs **first**. If you do not call `super(...)` explicitly, Java inserts `super()` for you, which fails to compile if the superclass has no no-argument constructor.

`final` on a class prevents subclassing; `final` on a method prevents overriding. Use inheritance only for a true **"is-a"** relationship (a `Manager` is an `Employee`). For **"has-a"** (a `Car` has an `Engine`) use composition.

### Program 4.1: Employee hierarchy
File: `Employee.java`
```java
public class Employee {
    private final String name;
    protected double baseSalary;      // EXPLAIN: protected -> visible to subclasses

    public Employee(String name, double baseSalary) {
        this.name = name;
        this.baseSalary = baseSalary;
        System.out.println("Employee constructor: " + name);
    }

    public String getName() { return name; }

    public double calculatePay() {
        return baseSalary;
    }

    public void describe() {
        System.out.printf("%s earns %.2f%n", name, calculatePay());
    }
}
```
File: `Manager.java`
```java
public class Manager extends Employee {     // EXPLAIN: Manager IS-A Employee
    private double bonus;

    public Manager(String name, double baseSalary, double bonus) {
        super(name, baseSalary);            // EXPLAIN: must be first; runs Employee's constructor
        this.bonus = bonus;
        System.out.println("Manager constructor: " + name);
    }

    @Override                               // EXPLAIN: compiler checks we really override
    public double calculatePay() {
        return super.calculatePay() + bonus; // reuse the parent's logic, then extend it
    }
}
```
File: `Developer.java`
```java
public class Developer extends Employee {
    private int overtimeHours;
    private static final double OVERTIME_RATE = 25.0;

    public Developer(String name, double baseSalary, int overtimeHours) {
        super(name, baseSalary);
        this.overtimeHours = overtimeHours;
    }

    @Override
    public double calculatePay() {
        return baseSalary + overtimeHours * OVERTIME_RATE;   // protected field used directly
    }
}
```
File: `CompanyDemo.java`
```java
public class CompanyDemo {
    public static void main(String[] args) {
        Employee e = new Employee("Kudzai", 2000);
        Manager m = new Manager("Nyasha", 3500, 800);
        Developer d = new Developer("Tatenda", 3000, 10);

        System.out.println("----");
        e.describe();
        m.describe();      // describe() is inherited; calculatePay() is Manager's version
        d.describe();
    }
}
```
**Expected output**
```text
Employee constructor: Kudzai
Employee constructor: Nyasha
Manager constructor: Nyasha
Employee constructor: Tatenda
----
Kudzai earns 2000.00
Nyasha earns 4300.00
Tatenda earns 3250.00
```
Notice that the superclass constructor message prints before the subclass message, and that `describe()` was written once but behaves differently for each object.

### Program 4.2: Constructor order with three levels
File: `Chain.java`
```java
class A {
    A() { System.out.println("A constructor"); }
}

class B extends A {
    B() { System.out.println("B constructor"); }   // implicit super() -> A()
}

class C extends B {
    C() { System.out.println("C constructor"); }   // implicit super() -> B()
}

public class Chain {
    public static void main(String[] args) {
        new C();
    }
}
```
**Expected output**
```text
A constructor
B constructor
C constructor
```

### Program 4.3: `final`, `Object` methods and composition
File: `CompositionDemo.java`
```java
class Engine {
    void start() { System.out.println("Engine started"); }
}

// EXPLAIN: final class -> cannot be extended
final class Car {
    private final Engine engine = new Engine();   // HAS-A relationship (composition)

    void drive() {
        engine.start();                            // delegate to the part
        System.out.println("Car is moving");
    }

    // EXPLAIN: every class inherits toString() from Object; we override it
    @Override
    public String toString() { return "Car with an engine"; }
}

public class CompositionDemo {
    public static void main(String[] args) {
        Car c = new Car();
        c.drive();
        System.out.println(c);          // implicitly calls c.toString()
    }
}
```

### Types of inheritance in Java

| Type | Supported for classes? | Notes |
| :--- | :--- | :--- |
| Single (`A` to `B`) | Yes | Standard form |
| Multilevel (`A` to `B` to `C`) | Yes | Constructors chain upwards |
| Hierarchical (`A` to `B` and `A` to `C`) | Yes | Shown in Program 4.1 |
| Multiple (`C` extends `A` and `B`) | No | Avoids the diamond problem; use interfaces (Lab 6) |

### Common mistakes
* Using inheritance just to reuse code when the relationship is not "is-a".
* Forgetting `super(...)` when the superclass has no no-argument constructor (compile error).
* Omitting `@Override`, which hides typos such as `calculatepay()` that create a new method instead of overriding.
* Making fields `protected` by default; prefer `private` with accessors.

### Exercises
1. **Basic:** Create `Person` (`name`, `age`) and `Student extends Person` (adds `course`). Print both using an inherited `display()`.
2. **Basic:** Add a `Teacher` subclass and show that `Student` and `Teacher` share the `Person` code.
3. **Intermediate:** Build `Vehicle`, `Car` and `ElectricCar` (multilevel). Trace and print the order of constructor calls.
4. **Intermediate:** Explain, with a code example, why `class Dog extends Animal` fails to compile when `Animal` only has `Animal(String name)`.
5. **Challenge:** Refactor a `Stack extends ArrayList` design into a `Stack` that contains an `ArrayList`. Explain what the inheritance version wrongly exposes.

---

## Lab 5: Polymorphism

### Objectives
* Distinguish compile-time polymorphism (overloading) from run-time polymorphism (overriding).
* Use a superclass reference to hold subclass objects (upcasting) and explain dynamic method dispatch.
* Downcast safely with `instanceof` and pattern matching.
* Write code that works with new subclasses without modification (the open/closed idea).

### Theory
**Polymorphism** means "many forms": the same call can behave differently depending on the object it is applied to.

| Kind | Also called | Decided | Mechanism |
| :--- | :--- | :--- | :--- |
| Method overloading | Static / compile-time | By the compiler, from argument types | Same name, different parameter lists in one class |
| Method overriding | Dynamic / run-time | By the JVM, from the actual object | Subclass redefines an inherited method with the same signature |

With overriding, the version that runs depends on the **object's real class**, not the variable's declared type. This is **dynamic method dispatch**. Fields and `static` methods are **not** polymorphic; they follow the declared type.

Overriding rules: same name and parameters; return type the same or a subtype; access no more restrictive; cannot override `final`, `static` or `private` methods.

### Program 5.1: Compile-time polymorphism (overloading)
File: `Calculator.java`
```java
public class Calculator {
    // EXPLAIN: same name, different parameter lists -> resolved at compile time
    int add(int a, int b)           { return a + b; }
    int add(int a, int b, int c)    { return a + b + c; }
    double add(double a, double b)  { return a + b; }
    String add(String a, String b)  { return a + b; }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        System.out.println(calc.add(2, 3));            // add(int,int)        -> 5
        System.out.println(calc.add(2, 3, 4));         // add(int,int,int)    -> 9
        System.out.println(calc.add(2.5, 3.5));        // add(double,double)  -> 6.0
        System.out.println(calc.add("Java", "OOP"));   // add(String,String)  -> JavaOOP
        System.out.println(calc.add(2, 3.5));          // int widened to double -> 5.5
    }
}
```
**Expected output**
```text
5
9
6.0
JavaOOP
5.5
```

### Program 5.2: Run-time polymorphism (overriding)
File: `Shapes.java`
```java
class Shape {
    String name() { return "Generic shape"; }
    double area() { return 0; }
}

class Circle extends Shape {
    private final double r;
    Circle(double r) { this.r = r; }
    @Override String name() { return "Circle"; }
    @Override double area() { return Math.PI * r * r; }
}

class Rectangle extends Shape {
    private final double w, h;
    Rectangle(double w, double h) { this.w = w; this.h = h; }
    @Override String name() { return "Rectangle"; }
    @Override double area() { return w * h; }
}

class Triangle extends Shape {
    private final double b, h;
    Triangle(double b, double h) { this.b = b; this.h = h; }
    @Override String name() { return "Triangle"; }
    @Override double area() { return 0.5 * b * h; }
}

public class Shapes {
    public static void main(String[] args) {
        // EXPLAIN: upcasting -> one array type holds many different subclass objects
        Shape[] shapes = {
            new Circle(5),
            new Rectangle(4, 6),
            new Triangle(3, 8)
        };

        double total = 0;
        for (Shape s : shapes) {
            // EXPLAIN: JVM picks name()/area() from the ACTUAL object at run time
            System.out.printf("%-10s area = %.2f%n", s.name(), s.area());
            total += s.area();
        }
        System.out.printf("Total area = %.2f%n", total);
    }
}
```
**Expected output**
```text
Circle     area = 78.54
Rectangle  area = 24.00
Triangle   area = 12.00
Total area = 114.54
```
Adding a `Square` class later requires no change to the loop; that is the practical value of polymorphism.

### Program 5.3: Casting, `instanceof` and what is NOT polymorphic
File: `CastingDemo.java`
```java
class Animal {
    String type = "Animal";                       // field
    void speak() { System.out.println("..."); }
    static void info() { System.out.println("Animal info"); }
}

class Dog extends Animal {
    String type = "Dog";                          // hides the field, does not override it
    @Override void speak() { System.out.println("Woof"); }
    void fetch() { System.out.println("Fetching..."); }
    static void info() { System.out.println("Dog info"); }   // hides, not overrides
}

public class CastingDemo {
    public static void main(String[] args) {
        Animal a = new Dog();          // upcast (implicit, always safe)
        a.speak();                     // Woof   -> method IS polymorphic
        System.out.println(a.type);    // Animal -> field follows the DECLARED type
        a.info();                      // Animal info -> static follows declared type
        // a.fetch();                  // COMPILE ERROR: Animal has no fetch()

        // EXPLAIN: downcast needs a check, otherwise ClassCastException is possible
        if (a instanceof Dog d) {      // pattern matching (Java 16+): test AND cast
            d.fetch();
        }

        Animal plain = new Animal();
        if (!(plain instanceof Dog)) {
            System.out.println("plain is not a Dog; downcast avoided");
        }
    }
}
```
**Expected output**
```text
Woof
Animal
Animal info
Fetching...
plain is not a Dog; downcast avoided
```

### Common mistakes
* Confusing overloading (same class, different parameters) with overriding (subclass, same signature).
* Expecting fields or `static` methods to behave polymorphically.
* Downcasting without `instanceof`, causing `ClassCastException`.
* Changing the parameter type when "overriding", which silently creates an overload; `@Override` catches this.

### Exercises
1. **Basic:** Overload a `print` method to accept `int`, `double`, `String` and `int[]`, and test each call.
2. **Basic:** Create `Animal` with `sound()` and subclasses `Cat`, `Cow`, `Duck`. Store them in an `Animal[]` and loop.
3. **Intermediate:** Extend the employee hierarchy of Lab 4 with `Intern`. Compute total payroll for a mixed array without changing the payroll loop.
4. **Intermediate:** Write a program whose output differs depending on whether a method or a field is accessed through a superclass reference, and explain why.
5. **Challenge:** Implement a small `PaymentProcessor.pay(Payment p)` that uses `instanceof` pattern matching for `CardPayment` and `MobileMoneyPayment`. Then refactor it to use a polymorphic `process()` method and compare the two designs.

---

## Lab 6: Abstraction, Abstract Classes and Interfaces

### Objectives
* Define abstract classes and abstract methods, and explain why they cannot be instantiated.
* Define and implement interfaces, including `default` and `static` methods.
* Choose correctly between an abstract class and an interface.
* Program to an interface, not to an implementation.
* Use standard interfaces such as `Comparable`.

### Theory
**Abstraction** exposes *what* an object does and hides *how* it does it. Java offers two tools:
* An **abstract class** is a partially built class. It may hold fields, constructors, concrete methods and `abstract` methods (declared without a body). Subclasses must implement every abstract method or be abstract themselves. It cannot be instantiated.
* An **interface** is a pure contract of capabilities. A class can implement many interfaces, which is how Java achieves multiple inheritance of type. Since Java 8 an interface may also provide `default` and `static` methods; since Java 9, `private` helper methods.

| Feature | Abstract class | Interface |
| :--- | :--- | :--- |
| Instance fields (state) | Yes | No (only `public static final` constants) |
| Constructors | Yes | No |
| Method bodies | Yes | Only `default`, `static`, `private` |
| Classes can extend/implement | Only one (`extends`) | Many (`implements`) |
| Best for | Shared code and state among closely related classes | A capability that unrelated classes can share |

### Program 6.1: Abstract class
File: `Vehicle.java`
```java
abstract class Vehicle {
    private final String plate;

    Vehicle(String plate) { this.plate = plate; }   // EXPLAIN: abstract classes CAN have constructors

    // EXPLAIN: abstract -> no body; every concrete subclass MUST provide one
    abstract double fuelCostPerKm();

    // concrete method shared by all vehicles (template of common behaviour)
    double tripCost(double km) {
        return km * fuelCostPerKm();
    }

    String getPlate() { return plate; }
}

class Truck extends Vehicle {
    Truck(String plate) { super(plate); }
    @Override double fuelCostPerKm() { return 0.80; }
}

class Motorbike extends Vehicle {
    Motorbike(String plate) { super(plate); }
    @Override double fuelCostPerKm() { return 0.15; }
}

public class VehicleDemo {
    public static void main(String[] args) {
        // Vehicle v = new Vehicle("X");   // COMPILE ERROR: Vehicle is abstract
        Vehicle[] fleet = { new Truck("TRK-1"), new Motorbike("MB-7") };
        for (Vehicle v : fleet) {
            System.out.printf("%s: 100 km costs %.2f%n", v.getPlate(), v.tripCost(100));
        }
    }
}
```
**Expected output**
```text
TRK-1: 100 km costs 80.00
MB-7: 100 km costs 15.00
```

### Program 6.2: Interfaces, default methods and multiple implementation
File: `InterfaceDemo.java`
```java
interface Payable {
    double MAX_LIMIT = 10_000;              // implicitly public static final

    void pay(double amount);                // implicitly public abstract

    // EXPLAIN: default method gives implementations for free; classes may override it
    default void receipt(double amount) {
        System.out.println("Receipt: paid " + amount);
    }

    // EXPLAIN: static utility method belongs to the interface itself
    static boolean withinLimit(double amount) {
        return amount <= MAX_LIMIT;
    }
}

interface Loggable {
    void log(String message);
}

// EXPLAIN: one class, several interfaces -> multiple inheritance of TYPE
class CardPayment implements Payable, Loggable {
    @Override
    public void pay(double amount) {
        if (!Payable.withinLimit(amount)) {
            log("Blocked: over limit");
            return;
        }
        log("Card charged " + amount);
        receipt(amount);
    }

    @Override
    public void log(String message) {           // interface methods must be public
        System.out.println("[LOG] " + message);
    }
}

class CashPayment implements Payable {
    @Override
    public void pay(double amount) {
        System.out.println("Cash received: " + amount);
    }

    @Override
    public void receipt(double amount) {        // overriding the default
        System.out.println("Handwritten receipt for " + amount);
    }
}

public class InterfaceDemo {
    public static void main(String[] args) {
        // EXPLAIN: program to the interface: the variable type is Payable
        Payable[] methods = { new CardPayment(), new CashPayment() };
        for (Payable p : methods) {
            p.pay(250);
            p.receipt(250);
            System.out.println("---");
        }
        new CardPayment().pay(50_000);
    }
}
```
**Expected output**
```text
[LOG] Card charged 250.0
Receipt: paid 250.0
Receipt: paid 250.0
---
Cash received: 250.0
Handwritten receipt for 250.0
---
[LOG] Blocked: over limit
```

### Program 6.3: `Comparable`, an interface from the Java library
File: `ComparableDemo.java`
```java
import java.util.Arrays;

class Player implements Comparable<Player> {
    private final String name;
    private final int score;

    Player(String name, int score) { this.name = name; this.score = score; }

    // EXPLAIN: defines the natural ordering: highest score first
    @Override
    public int compareTo(Player other) {
        return Integer.compare(other.score, this.score);
    }

    @Override
    public String toString() { return name + "(" + score + ")"; }
}

public class ComparableDemo {
    public static void main(String[] args) {
        Player[] players = {
            new Player("Anesu", 70), new Player("Blessing", 95), new Player("Chenai", 82)
        };
        Arrays.sort(players);          // sort relies only on the Comparable contract
        System.out.println(Arrays.toString(players));
    }
}
```
**Expected output**
```text
[Blessing(95), Chenai(82), Anesu(70)]
```

### Common mistakes
* Trying to instantiate an abstract class or an interface with `new` (only allowed with an anonymous class body).
* Implementing an interface method without `public`, which reduces visibility and fails to compile.
* Adding state to a design that really needs an interface, or using an interface where subclasses need shared fields.
* Forgetting to implement all abstract methods, so the subclass must itself be declared `abstract`.

### Exercises
1. **Basic:** Create an `abstract Shape` with abstract `area()` and `perimeter()`, and implement `Circle`, `Rectangle` and `Square`.
2. **Basic:** Define an interface `Flyable` with `fly()` and implement it for `Bird` and `Aeroplane`, which share no superclass.
3. **Intermediate:** Build an abstract class `Account` with a concrete `deposit()` and an abstract `monthlyFee()`. Implement `SavingsAccount` and `CurrentAccount`.
4. **Intermediate:** Make `Student implements Comparable<Student>` sorted by name, then add a `Comparator` sorted by marks.
5. **Challenge:** Two interfaces both declare a `default void hello()`. Write a class that implements both, resolve the conflict with `InterfaceName.super.hello()`, and explain the rule.

---

## Lab 7: Exception Handling

### Objectives
* Explain the exception hierarchy and the difference between checked and unchecked exceptions.
* Use `try`, `catch`, `finally`, `throw` and `throws` correctly.
* Create custom exceptions that carry useful information.
* Manage resources safely with `try`-with-resources.
* Write methods that fail predictably instead of leaving objects in a bad state.

### Theory
An **exception** is an object representing an abnormal event during execution. When one is thrown, normal flow stops and the JVM searches up the call stack for a matching `catch` block.

```text
Throwable
├── Error                      (serious JVM problems: OutOfMemoryError, StackOverflowError; do not catch)
└── Exception
    ├── IOException, SQLException, ...   CHECKED: must be caught or declared with throws
    └── RuntimeException                 UNCHECKED: bugs; catching is optional
        ├── NullPointerException
        ├── ArithmeticException
        ├── IllegalArgumentException
        └── ArrayIndexOutOfBoundsException
```

| Keyword | Purpose |
| :--- | :--- |
| `try` | Wraps code that might fail |
| `catch` | Handles a specific exception type |
| `finally` | Always runs (cleanup), whether or not an exception occurred |
| `throw` | Throws one exception object right now |
| `throws` | Declares in a method signature which checked exceptions it may throw |

`catch` blocks are tried top to bottom, so order them from **most specific to most general**; a general catch placed first makes later ones unreachable and does not compile.

### Program 7.1: `try`, `catch`, `finally`
File: `ExceptionBasics.java`
```java
public class ExceptionBasics {
    static int divide(int a, int b) {
        return a / b;                       // ArithmeticException if b == 0
    }

    public static void main(String[] args) {
        int[] data = {10, 20, 30};
        String text = null;

        try {
            System.out.println("10 / 2 = " + divide(10, 2));
            System.out.println("10 / 0 = " + divide(10, 0));   // throws here
            System.out.println("never printed");
        } catch (ArithmeticException e) {
            System.out.println("Math problem: " + e.getMessage());
        } finally {
            System.out.println("finally always runs");
        }

        // EXPLAIN: several catch blocks, specific first
        try {
            System.out.println(data[5]);
            System.out.println(text.length());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Bad index: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Null reference");
        } catch (Exception e) {                 // most general last
            System.out.println("Something else: " + e);
        }

        // EXPLAIN: multi-catch when the handling is identical
        try {
            Integer.parseInt("abc");
        } catch (NumberFormatException | NullPointerException e) {
            System.out.println("Invalid number input");
        }
    }
}
```
**Expected output**
```text
10 / 2 = 5
Math problem: / by zero
finally always runs
Bad index: Index 5 out of bounds for length 3
Invalid number input
```

### Program 7.2: Custom checked exception
File: `InsufficientFundsException.java`
```java
// EXPLAIN: extending Exception makes this a CHECKED exception
public class InsufficientFundsException extends Exception {
    private final double shortfall;

    public InsufficientFundsException(String message, double shortfall) {
        super(message);                     // pass the message to Exception
        this.shortfall = shortfall;
    }

    public double getShortfall() { return shortfall; }
}
```
File: `Wallet.java`
```java
public class Wallet {
    private double balance;

    public Wallet(double balance) { this.balance = balance; }

    // EXPLAIN: 'throws' warns callers they must handle this checked exception
    public void spend(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            // EXPLAIN: 'throw' creates and throws the exception object
            throw new InsufficientFundsException(
                "Cannot spend " + amount + "; balance is " + balance, amount - balance);
        }
        balance -= amount;
    }

    public double getBalance() { return balance; }

    public static void main(String[] args) {
        Wallet w = new Wallet(100);
        try {
            w.spend(60);
            System.out.println("Balance now: " + w.getBalance());
            w.spend(60);                    // fails
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());
            System.out.println("Short by: " + e.getShortfall());
        }
    }
}
```
**Expected output**
```text
Balance now: 40.0
Cannot spend 60.0; balance is 40.0
Short by: 20.0
```

### Program 7.3: `try`-with-resources and exception chaining
File: `ResourceDemo.java`
```java
class Connection implements AutoCloseable {     // EXPLAIN: AutoCloseable enables try-with-resources
    private final String name;

    Connection(String name) {
        this.name = name;
        System.out.println(name + " opened");
    }

    void query(boolean fail) {
        if (fail) throw new IllegalStateException(name + " query failed");
        System.out.println(name + " query ok");
    }

    @Override
    public void close() {
        System.out.println(name + " closed");   // called automatically
    }
}

public class ResourceDemo {
    public static void main(String[] args) {
        // EXPLAIN: resources close in REVERSE order, even if an exception occurs
        try (Connection c1 = new Connection("DB1");
             Connection c2 = new Connection("DB2")) {
            c1.query(false);
            c2.query(true);
        } catch (IllegalStateException e) {
            System.out.println("Caught: " + e.getMessage());
        }

        // EXPLAIN: wrapping a low-level exception keeps the original as the cause
        try {
            try {
                Integer.parseInt("12x");
            } catch (NumberFormatException e) {
                throw new RuntimeException("Configuration invalid", e);
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage() + " <- " + e.getCause().getClass().getSimpleName());
        }
    }
}
```
**Expected output**
```text
DB1 opened
DB2 opened
DB1 query ok
DB2 closed
DB1 closed
Caught: DB2 query failed
Configuration invalid <- NumberFormatException
```

### Best practices
* Catch the most specific exception you can actually handle; never write an empty `catch` block.
* Use unchecked exceptions (`IllegalArgumentException`, `IllegalStateException`) for programming errors and checked exceptions for recoverable conditions.
* Do not use exceptions for ordinary control flow.
* Prefer `try`-with-resources to manual `finally` cleanup.
* Include useful context in exception messages.

### Exercises
1. **Basic:** Read two integers from the user (`Scanner`) and divide them, handling both non-numeric input and division by zero.
2. **Basic:** Write a method that accesses an array element and catches `ArrayIndexOutOfBoundsException`.
3. **Intermediate:** Create `InvalidAgeException` (checked) and a `validateAge(int)` method that throws it for values outside 0 to 120.
4. **Intermediate:** Modify `BankAccount` (Lab 3) so `withdraw` throws `InsufficientFundsException` instead of returning `false`. Discuss the trade-offs.
5. **Challenge:** Predict the output of a method with `return` inside `try` and a statement that prints inside `finally`. Verify and explain why `finally` still executes.

---

## Lab 8: Generics and the Collections Framework

### Objectives
* Write generic classes and methods, including bounded type parameters.
* Explain type erasure and why primitives need wrapper classes.
* Choose the right collection: `List`, `Set`, `Map`, `Queue`.
* Sort and search with `Comparable`, `Comparator` and `Collections`.
* Program to the interface (`List<String> x = new ArrayList<>()`).

### Theory
**Generics** let a class or method work on a type supplied by the caller (`Box<String>`, `Box<Integer>`), moving type errors from run time to compile time and removing casts. The compiler erases the type parameter after checking (**type erasure**), so generics work only with reference types; use wrappers such as `Integer` for primitives (autoboxing converts automatically).

| Interface | Ordered? | Duplicates? | Typical implementations | Use when |
| :--- | :--- | :--- | :--- | :--- |
| `List` | Yes (by index) | Yes | `ArrayList`, `LinkedList` | Sequence with fast lookup by position |
| `Set` | No (`HashSet`), sorted (`TreeSet`), insertion (`LinkedHashSet`) | No | `HashSet`, `TreeSet` | Uniqueness matters |
| `Map` | Keys unique | Values may repeat | `HashMap`, `TreeMap`, `LinkedHashMap` | Look up value by key |
| `Queue` / `Deque` | FIFO / both ends | Yes | `ArrayDeque`, `PriorityQueue` | Processing in arrival or priority order |

### Program 8.1: Generic class and generic methods
File: `GenericsDemo.java`
```java
import java.util.List;

// EXPLAIN: T is a type parameter chosen by the caller
class Box<T> {
    private T content;
    Box(T content) { this.content = content; }
    T get()          { return content; }
    void set(T value) { this.content = value; }
}

// EXPLAIN: two type parameters
class Pair<K, V> {
    private final K key;
    private final V value;
    Pair(K key, V value) { this.key = key; this.value = value; }
    K getKey()   { return key; }
    V getValue() { return value; }
    @Override public String toString() { return key + "=" + value; }
}

public class GenericsDemo {
    // EXPLAIN: generic METHOD; <T> before the return type declares the parameter
    static <T> void printAll(List<T> items) {
        for (T item : items) System.out.print(item + " ");
        System.out.println();
    }

    // EXPLAIN: bounded type: T must be a Comparable<T>, so compareTo is available
    static <T Comparable<T extends>> T maxOf(List<T> items) {
        T best = items.get(0);
        for (T item : items) {
            if (item.compareTo(best) > 0) best = item;
        }
        return best;
    }

    public static void main(String[] args) {
        Box<String> sb = new Box<>("Hello");
        Box<Integer> ib = new Box<>(42);           // autoboxing: int -> Integer

        String s = sb.get();                        // no cast needed
        int n = ib.get() + 1;
        // sb.set(99);                              // COMPILE ERROR: type safety
        System.out.println(s + " " + n);

        Pair<String, Integer> p = new Pair<>("age", 21);
        System.out.println(p);

        printAll(List.of(1, 2, 3));
        printAll(List.of("a", "b"));
        System.out.println(maxOf(List.of(3, 9, 4)));
        System.out.println(maxOf(List.of("pear", "apple", "zebra")));
    }
}
```
**Expected output**
```text
Hello 43
age=21
1 2 3
a b
9
zebra
```

### Program 8.2: `List`, `Set` and `Map`
File: `CollectionsDemo.java`
```java
import java.util.*;

public class CollectionsDemo {
    public static void main(String[] args) {
        // ---- List: ordered, allows duplicates ----
        List<String> names = new ArrayList<>();   // programming to the interface
        names.add("Zodwa");
        names.add("Alice");
        names.add("Moyo");
        names.add("Alice");
        System.out.println("List: " + names + " size=" + names.size());
        names.remove("Moyo");
        Collections.sort(names);                  // natural (alphabetical) order
        System.out.println("Sorted: " + names);

        // ---- Set: no duplicates ----
        Set<String> unique = new TreeSet<>(names); // TreeSet keeps elements sorted
        System.out.println("Set: " + unique);

        // ---- Map: key -> value ----
        Map<String, Integer> marks = new HashMap<>();
        marks.put("Tino", 78);
        marks.put("Rufaro", 91);
        marks.put("Tino", 82);                    // same key -> value replaced
        marks.putIfAbsent("Nomsa", 65);
        System.out.println("Tino: " + marks.get("Tino"));
        System.out.println("Has Nomsa? " + marks.containsKey("Nomsa"));

        // iterate a map in key order using TreeMap
        for (Map.Entry<String, Integer> e : new TreeMap<>(marks).entrySet()) {
            System.out.println(e.getKey() + " -> " + e.getValue());
        }

        // ---- Word frequency: a classic Map use ----
        String text = "to be or not to be";
        Map<String, Integer> freq = new TreeMap<>();
        for (String w : text.split(" ")) {
            freq.merge(w, 1, Integer::sum);       // add 1, or start at 1
        }
        System.out.println(freq);

        // ---- Queue / Deque ----
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(1); stack.push(2); stack.push(3);
        System.out.println("Stack pop: " + stack.pop());   // LIFO -> 3

        Queue<String> line = new ArrayDeque<>();
        line.offer("first"); line.offer("second");
        System.out.println("Queue poll: " + line.poll());  // FIFO -> first
    }
}
```
**Expected output**
```text
List: [Zodwa, Alice, Moyo, Alice] size=4
Sorted: [Alice, Alice, Zodwa]
Set: [Alice, Zodwa]
Tino: 82
Has Nomsa? true
Nomsa -> 65
Rufaro -> 91
Tino -> 82
{be=2, not=1, or=1, to=2}
Stack pop: 3
Queue poll: first
```

### Program 8.3: Sorting objects with `Comparable` and `Comparator`
File: `SortingDemo.java`
```java
import java.util.*;

class Student implements Comparable<Student> {
    private final String name;
    private final double gpa;

    Student(String name, double gpa) { this.name = name; this.gpa = gpa; }
    String getName() { return name; }
    double getGpa()  { return gpa; }

    // EXPLAIN: natural order = by name (one default ordering per class)
    @Override
    public int compareTo(Student o) { return name.compareTo(o.name); }

    @Override public String toString() { return name + "(" + gpa + ")"; }
}

public class SortingDemo {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<>(List.of(
            new Student("Kuda", 3.2), new Student("Ada", 3.9), new Student("Lynn", 3.5)));

        Collections.sort(list);                             // uses compareTo
        System.out.println("By name: " + list);

        // EXPLAIN: Comparator = an external, alternative ordering (any number of them)
        list.sort(Comparator.comparingDouble(Student::getGpa).reversed());
        System.out.println("By GPA desc: " + list);

        list.sort(Comparator.comparing(Student::getName).reversed());
        System.out.println("By name desc: " + list);
    }
}
```
**Expected output**
```text
By name: [Ada(3.9), Kuda(3.2), Lynn(3.5)]
By GPA desc: [Ada(3.9), Lynn(3.5), Kuda(3.2)]
By name desc: [Lynn(3.5), Kuda(3.2), Ada(3.9)]
```

### Common mistakes
* Using raw types (`List` instead of `List<String>`), which disables compile-time checks.
* Removing elements from a collection inside a for-each loop (`ConcurrentModificationException`); use an `Iterator` or `removeIf`.
* Using mutable objects as `HashMap` keys and then changing them.
* Forgetting that `List.of(...)` returns an immutable list.

### Exercises
1. **Basic:** Store 10 integers in an `ArrayList`, then print the sum, maximum and the list in reverse order.
2. **Basic:** Remove duplicates from a list of names using a `Set`.
3. **Intermediate:** Write a generic `Stack<T>` class backed by an `ArrayList<T>` with `push`, `pop`, `peek` and `isEmpty`.
4. **Intermediate:** Read a paragraph and print the frequency of each word using a `Map`, sorted by frequency descending.
5. **Challenge:** Implement a generic method `<T extends Comparable<T>> void bubbleSort(T[] arr)` and test it on `Integer[]` and `String[]`. Explain why `int[]` will not work.

---

## Lab 9: Nested Types, Enums, Records and the Object Contract

### Objectives
* Distinguish static nested, inner (member), local and anonymous classes.
* Model fixed sets of constants with `enum`, including fields, constructors and methods.
* Create immutable data carriers with `record`.
* Override `equals`, `hashCode` and `toString` correctly and explain why they must be consistent.

### Theory
**Nested classes** are classes declared inside another class, used when a helper type belongs closely to its outer class.

| Kind | Declared | Has access to outer instance? | Typical use |
| :--- | :--- | :--- | :--- |
| Static nested | `static class X` inside a class | No (only outer static members) | Helper such as `Node` or `Builder` |
| Inner (member) | `class X` inside a class, no `static` | Yes, holds a hidden reference to the outer object | Iterator over the outer's data |
| Local | Inside a method body | Yes, plus effectively final locals | Rare, one-off helper |
| Anonymous | `new Interface() { ... }` expression | Yes | One-time implementation of an interface or abstract class |

An **`enum`** is a class with a fixed set of instances; it is type-safe and can be used in `switch`. A **`record`** (Java 16+) is a compact, immutable class whose components automatically get a constructor, accessors, `equals`, `hashCode` and `toString`.

The **`equals`/`hashCode` contract**: if `a.equals(b)` is `true` then `a.hashCode() == b.hashCode()`. Breaking it makes objects misbehave inside `HashSet` and `HashMap`.

### Program 9.1: The four kinds of nested class
File: `NestedDemo.java`
```java
import java.util.Iterator;

public class NestedDemo {
    private final int[] data = {10, 20, 30};
    private static int instances = 0;

    NestedDemo() { instances++; }

    // 1) STATIC NESTED: no outer instance needed
    static class Helper {
        static String describe() { return "Instances created: " + instances; }
    }

    // 2) INNER CLASS: can read the outer object's private fields
    class DataIterator implements Iterator<Integer> {
        private int index = 0;
        public boolean hasNext() { return index < data.length; }   // 'data' belongs to outer
        public Integer next()    { return data[index++]; }
    }

    public static void main(String[] args) {
        NestedDemo outer = new NestedDemo();
        System.out.println(Helper.describe());

        // EXPLAIN: inner class instance requires an outer instance
        NestedDemo.DataIterator it = outer.new DataIterator();
        while (it.hasNext()) System.out.print(it.next() + " ");
        System.out.println();

        // 3) LOCAL CLASS
        class Greeter {
            String greet(String n) { return "Hello, " + n; }
        }
        System.out.println(new Greeter().greet("class"));

        // 4) ANONYMOUS CLASS: implements Runnable on the spot
        Runnable task = new Runnable() {
            @Override public void run() { System.out.println("Anonymous task running"); }
        };
        task.run();

        // Modern alternative for single-method interfaces: a lambda (Lab 10)
        Runnable lambdaTask = () -> System.out.println("Lambda task running");
        lambdaTask.run();
    }
}
```
**Expected output**
```text
Instances created: 1
10 20 30
Hello, class
Anonymous task running
Lambda task running
```

### Program 9.2: Enums
File: `EnumDemo.java`
```java
enum Day { MON, TUE, WED, THU, FRI, SAT, SUN }

// EXPLAIN: an enum can have fields, a private constructor and methods
enum Planet {
    MERCURY(3.303e+23, 2.4397e6),
    EARTH(5.976e+24, 6.37814e6);

    private final double mass;      // kg
    private final double radius;    // m

    Planet(double mass, double radius) { this.mass = mass; this.radius = radius; }

    static final double G = 6.67300E-11;
    double surfaceGravity() { return G * mass / (radius * radius); }
}

// EXPLAIN: each constant may override an abstract method (constant-specific body)
enum Operation {
    ADD("+") { double apply(double a, double b) { return a + b; } },
    MULTIPLY("*") { double apply(double a, double b) { return a * b; } };

    private final String symbol;
    Operation(String symbol) { this.symbol = symbol; }
    abstract double apply(double a, double b);
    String symbol() { return symbol; }
}

public class EnumDemo {
    static String kind(Day d) {
        return switch (d) {                    // EXPLAIN: switch expression; compiler checks all cases
            case SAT, SUN -> "Weekend";
            default -> "Weekday";
        };
    }

    public static void main(String[] args) {
        Day today = Day.WED;
        System.out.println(today + " is a " + kind(today) + ", ordinal " + today.ordinal());
        for (Day d : Day.values()) System.out.print(d + " ");
        System.out.println();

        System.out.printf("Earth gravity: %.2f m/s^2%n", Planet.EARTH.surfaceGravity());
        System.out.println(Day.valueOf("FRI"));

        for (Operation op : Operation.values()) {
            System.out.println("6 " + op.symbol() + " 7 = " + op.apply(6, 7));
        }
    }
}
```
**Expected output**
```text
WED is a Weekday, ordinal 2
MON TUE WED THU FRI SAT SUN
Earth gravity: 9.80 m/s^2
FRI
6 + 7 = 13.0
6 * 7 = 42.0
```

### Program 9.3: `equals`, `hashCode`, `toString` and records
File: `ObjectContract.java`
```java
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class Point {
    private final int x, y;
    Point(int x, int y) { this.x = x; this.y = y; }

    // EXPLAIN: equality by VALUE instead of by reference
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;                    // same reference
        if (!(o instanceof Point other)) return false; // null or wrong type
        return x == other.x && y == other.y;
    }

    // EXPLAIN: equal objects MUST produce equal hash codes
    @Override
    public int hashCode() { return Objects.hash(x, y); }

    @Override
    public String toString() { return "Point(" + x + ", " + y + ")"; }
}

// The same idea with almost no code: a record
record Coordinate(int x, int y) {
    // compact constructor: validation
    Coordinate {
        if (x < 0 || y < 0) throw new IllegalArgumentException("Negative coordinate");
    }
    double distanceFromOrigin() { return Math.sqrt(x * x + y * y); }
}

public class ObjectContract {
    public static void main(String[] args) {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(1, 2);
        System.out.println(p1 == p2);          // false (different objects)
        System.out.println(p1.equals(p2));     // true  (same values)

        Set<Point> set = new HashSet<>();
        set.add(p1);
        set.add(p2);                           // duplicate by equals/hashCode
        System.out.println("Set size: " + set.size());   // 1
        System.out.println(p1);

        Coordinate c1 = new Coordinate(3, 4);
        Coordinate c2 = new Coordinate(3, 4);
        System.out.println(c1);                          // generated toString
        System.out.println(c1.equals(c2));               // generated equals
        System.out.println(c1.x() + " " + c1.distanceFromOrigin());   // accessor: x()
    }
}
```
**Expected output**
```text
false
true
Set size: 1
Point(1, 2)
Coordinate[x=3, y=4]
true
3 5.0
```

### Common mistakes
* Overriding `equals` without `hashCode`; the object then "disappears" from hash-based collections.
* Writing `equals(Point p)` instead of `equals(Object o)`, which overloads rather than overrides.
* Instantiating an inner class from a static context without an outer instance.
* Using `==` to compare enum constants is correct, but `==` on `String` objects is not.

### Exercises
1. **Basic:** Define an `enum Level { LOW, MEDIUM, HIGH }` and a `switch` that prints advice for each.
2. **Basic:** Create a `record Student(String name, int age)` and print it, compare two equal records and read a component.
3. **Intermediate:** Add `equals`/`hashCode` to a `Book` class (by ISBN) and prove that a `HashSet<Book>` rejects duplicates.
4. **Intermediate:** Write a `LinkedList` class with a `private static` nested `Node` class, and an inner `Iterator` class so the list works in a for-each loop.
5. **Challenge:** Create an `enum Coin` with values `PENNY(1)`, `NICKEL(5)`, `DIME(10)`, `QUARTER(25)` and a method that returns the fewest coins for a given amount.

---

## Lab 10: File I/O, Lambdas and Streams

### Objectives
* Read and write text files with `java.nio.file` and handle `IOException`.
* Persist and restore objects using a simple CSV format.
* Write lambda expressions and method references, and identify functional interfaces.
* Process collections declaratively with the Stream API (`filter`, `map`, `sorted`, `collect`, `reduce`).

### Theory
File operations can fail for reasons outside the program's control, so most I/O methods throw the checked `IOException`; handle it with `try`/`catch` or declare it with `throws`.

A **functional interface** has exactly one abstract method (`Runnable`, `Comparator<T>`, `Function<T,R>`, `Predicate<T>`, `Consumer<T>`, `Supplier<T>`). A **lambda** `(params) -> expression` is a short way to supply that method. A **method reference** such as `String::length` is a lambda that just calls an existing method.

A **stream** is a pipeline: a source (a collection), zero or more lazy intermediate operations (`filter`, `map`, `sorted`), and one terminal operation (`collect`, `forEach`, `count`, `sum`) that triggers the work. Streams do not modify their source.

| Functional interface | Method | Example lambda |
| :--- | :--- | :--- |
| `Predicate<T>` | `boolean test(T)` | `s -> s.length() > 3` |
| `Function<T,R>` | `R apply(T)` | `s -> s.toUpperCase()` |
| `Consumer<T>` | `void accept(T)` | `s -> System.out.println(s)` |
| `Supplier<T>` | `T get()` | `() -> new ArrayList<String>()` |
| `Comparator<T>` | `int compare(T,T)` | `(a, b) -> a.length() - b.length()` |

### Program 10.1: Reading and writing text files
File: `FileDemo.java`
```java
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class FileDemo {
    public static void main(String[] args) {
        Path path = Path.of("students.txt");

        // ---- WRITE ----
        List<String> lines = List.of("Tariro,101,3.8", "Farai,102,3.1", "Rudo,103,3.9");
        try {
            Files.write(path, lines);                       // creates or overwrites
            Files.writeString(path, "Kuda,104,3.2\n", StandardOpenOption.APPEND);
            System.out.println("File written: " + Files.size(path) + " bytes");
        } catch (IOException e) {
            System.out.println("Write failed: " + e.getMessage());
            return;
        }

        // ---- READ (try-with-resources, streams a large file line by line) ----
        try (var reader = Files.newBufferedReader(path)) {
            String line;
            int count = 0;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                System.out.printf("%-8s roll=%s gpa=%s%n", parts[0], parts[1], parts[2]);
                count++;
            }
            System.out.println(count + " records read");
        } catch (IOException e) {
            System.out.println("Read failed: " + e.getMessage());
        }

        // ---- HANDLING A MISSING FILE ----
        try {
            Files.readAllLines(Path.of("does-not-exist.txt"));
        } catch (NoSuchFileException e) {
            System.out.println("Missing file: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O error");
        }
    }
}
```
**Expected output**
```text
File written: 55 bytes
Tariro   roll=101 gpa=3.8
Farai    roll=102 gpa=3.1
Rudo     roll=103 gpa=3.9
Kuda     roll=104 gpa=3.2
4 records read
Missing file: does-not-exist.txt
```
*(The byte count depends on line endings; on Windows it will differ slightly.)*

### Program 10.2: Saving and loading objects as CSV
File: `StudentRepository.java`
```java
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

record Learner(String name, int roll, double gpa) {
    String toCsv() { return name + "," + roll + "," + gpa; }

    // EXPLAIN: a static factory that parses one CSV line back into an object
    static Learner fromCsv(String line) {
        String[] p = line.split(",");
        return new Learner(p[0], Integer.parseInt(p[1]), Double.parseDouble(p[2]));
    }
}

public class StudentRepository {
    private final Path file;

    public StudentRepository(String fileName) { this.file = Path.of(fileName); }

    public void saveAll(List<Learner> learners) throws IOException {
        List<String> lines = new ArrayList<>();
        for (Learner l : learners) lines.add(l.toCsv());
        Files.write(file, lines);
    }

    public List<Learner> loadAll() throws IOException {
        List<Learner> result = new ArrayList<>();
        if (!Files.exists(file)) return result;          // first run: nothing saved yet
        for (String line : Files.readAllLines(file)) {
            if (!line.isBlank()) result.add(Learner.fromCsv(line));
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        StudentRepository repo = new StudentRepository("learners.csv");
        repo.saveAll(List.of(new Learner("Ada", 1, 3.9), new Learner("Kuda", 2, 3.2)));
        for (Learner l : repo.loadAll()) System.out.println(l);
    }
}
```
**Expected output**
```text
Learner[name=Ada, roll=1, gpa=3.9]
Learner[name=Kuda, roll=2, gpa=3.2]
```

### Program 10.3: Lambdas, method references and streams
File: `StreamDemo.java`
```java
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

record Employee(String name, String dept, double salary) {}

public class StreamDemo {
    public static void main(String[] args) {
        // ---- Lambdas and functional interfaces ----
        Predicate<String> longWord = s -> s.length() > 4;
        Function<String, Integer> length = String::length;        // method reference
        Consumer<String> shout = s -> System.out.println(s.toUpperCase() + "!");
        Supplier<List<String>> maker = ArrayList::new;

        System.out.println(longWord.test("lambda") + " " + length.apply("stream"));
        shout.accept("hello");
        List<String> empty = maker.get();

        // ---- Streams over a list ----
        List<Employee> staff = List.of(
            new Employee("Ada", "IT", 4200),
            new Employee("Ben", "IT", 3800),
            new Employee("Cara", "HR", 3000),
            new Employee("Dan", "Finance", 5000),
            new Employee("Eve", "HR", 3400));

        // filter -> map -> collect
        List<String> highEarners = staff.stream()
            .filter(e -> e.salary() >= 3800)
            .map(Employee::name)
            .collect(Collectors.toList());
        System.out.println("High earners: " + highEarners);

        // sorted with a Comparator, limit, forEach
        staff.stream()
            .sorted(Comparator.comparingDouble(Employee::salary).reversed())
            .limit(2)
            .forEach(e -> System.out.println("Top: " + e.name()));

        // reduce / numeric summaries
        double total = staff.stream().mapToDouble(Employee::salary).sum();
        double average = staff.stream().mapToDouble(Employee::salary).average().orElse(0);
        System.out.printf("Total %.0f, average %.1f%n", total, average);

        // groupingBy: a Map from department to employees / to a statistic
        Map<String, Long> countByDept = staff.stream()
            .collect(Collectors.groupingBy(Employee::dept, TreeMap::new, Collectors.counting()));
        System.out.println("Head count: " + countByDept);

        Map<String, Double> avgByDept = staff.stream()
            .collect(Collectors.groupingBy(Employee::dept, TreeMap::new,
                     Collectors.averagingDouble(Employee::salary)));
        System.out.println("Average pay: " + avgByDept);

        // anyMatch / joining
        System.out.println("Anyone in Finance? " + staff.stream().anyMatch(e -> e.dept().equals("Finance")));
        System.out.println(staff.stream().map(Employee::name).collect(Collectors.joining(", ", "[", "]")));
    }
}
```
**Expected output**
```text
true 6
HELLO!
High earners: [Ada, Ben, Dan]
Top: Dan
Top: Ada
Total 19400, average 3880.0
Head count: {Finance=1, HR=2, IT=2}
Average pay: {Finance=5000.0, HR=3200.0, IT=4000.0}
Anyone in Finance? true
[Ada, Ben, Cara, Dan, Eve]
```

### Common mistakes
* Ignoring `IOException` with an empty catch; at least report the message.
* Assuming a relative path resolves next to the source file; it resolves against the working directory.
* Reusing a stream after a terminal operation (`IllegalStateException`).
* Modifying external state inside a stream lambda, which makes results unpredictable.

### Exercises
1. **Basic:** Write a program that saves five lines typed by the user to a file and then reads them back.
2. **Basic:** Use a `Predicate` and `removeIf` to delete all even numbers from a list.
3. **Intermediate:** Read a text file and print the number of lines, words and characters.
4. **Intermediate:** Given a list of `Student` records, use streams to print the names of those above the class average, sorted alphabetically.
5. **Challenge:** Load learners from `learners.csv`, group them by grade band (`A`, `B`, `C`) with `Collectors.groupingBy`, and write one report file per band.

---

## Lab 11: Design Patterns and the Mini-Project

### Objectives
* Recognise four common patterns (Singleton, Factory, Strategy, Observer) and the problem each one solves.
* Apply SOLID-style thinking: single responsibility, open for extension, program to interfaces.
* Combine classes, interfaces, collections, exceptions and file I/O in one working application.

### Theory
A **design pattern** is a proven, named solution to a recurring design problem. It is a template for structuring classes, not a library. Use a pattern only when its problem is actually present.

| Pattern | Problem it solves | OOP principle it shows |
| :--- | :--- | :--- |
| Singleton | Exactly one shared instance (configuration, logger) | Encapsulation of construction |
| Factory | Creating objects without hard-coding concrete classes | Polymorphism, abstraction |
| Strategy | Swapping an algorithm at run time | Composition over inheritance |
| Observer | Notifying many objects when one changes | Loose coupling via interfaces |

### Program 11.1: Singleton and Factory
File: `SingletonFactoryDemo.java`
```java
// ---------- SINGLETON ----------
class AppConfig {
    private static final AppConfig INSTANCE = new AppConfig();   // created once
    private final String institution = "Example University";

    private AppConfig() { }                       // EXPLAIN: private constructor blocks 'new'

    public static AppConfig getInstance() { return INSTANCE; }
    public String getInstitution() { return institution; }
}

// ---------- FACTORY ----------
interface Notification {
    void send(String message);
}

class EmailNotification implements Notification {
    public void send(String m) { System.out.println("Email: " + m); }
}

class SmsNotification implements Notification {
    public void send(String m) { System.out.println("SMS: " + m); }
}

class NotificationFactory {
    // EXPLAIN: callers depend on the Notification interface, never on the concrete classes
    static Notification create(String type) {
        return switch (type.toLowerCase()) {
            case "email" -> new EmailNotification();
            case "sms"   -> new SmsNotification();
            default -> throw new IllegalArgumentException("Unknown type: " + type);
        };
    }
}

public class SingletonFactoryDemo {
    public static void main(String[] args) {
        AppConfig a = AppConfig.getInstance();
        AppConfig b = AppConfig.getInstance();
        System.out.println("Same instance? " + (a == b) + " -> " + a.getInstitution());

        NotificationFactory.create("email").send("Lab 11 is open");
        NotificationFactory.create("SMS").send("Lab 11 is open");
    }
}
```
**Expected output**
```text
Same instance? true -> Example University
Email: Lab 11 is open
SMS: Lab 11 is open
```

### Program 11.2: Strategy and Observer
File: `StrategyObserverDemo.java`
```java
import java.util.ArrayList;
import java.util.List;

// ---------- STRATEGY ----------
interface DiscountStrategy {
    double apply(double price);
}

class Checkout {
    private DiscountStrategy strategy;
    Checkout(DiscountStrategy strategy) { this.strategy = strategy; }
    void setStrategy(DiscountStrategy s) { this.strategy = s; }     // change behaviour at run time
    double total(double price) { return strategy.apply(price); }
}

// ---------- OBSERVER ----------
interface GradeListener {
    void onGradePublished(String course, int mark);
}

class GradeBook {
    private final List<GradeListener> listeners = new ArrayList<>();
    void subscribe(GradeListener l) { listeners.add(l); }
    void publish(String course, int mark) {
        for (GradeListener l : listeners) l.onGradePublished(course, mark);   // notify all
    }
}

public class StrategyObserverDemo {
    public static void main(String[] args) {
        // Strategies are one-method interfaces, so lambdas work well
        Checkout checkout = new Checkout(price -> price);              // no discount
        System.out.println("Regular: " + checkout.total(200));

        checkout.setStrategy(price -> price * 0.90);                   // 10% student discount
        System.out.println("Student: " + checkout.total(200));

        GradeBook book = new GradeBook();
        book.subscribe((course, mark) -> System.out.println("Student portal: " + course + " = " + mark));
        book.subscribe((course, mark) -> {
            if (mark < 50) System.out.println("Advisor alert: " + course + " failed");
        });

        book.publish("OOP", 72);
        book.publish("Databases", 45);
    }
}
```
**Expected output**
```text
Regular: 200.0
Student: 180.0
Student portal: OOP = 72
Student portal: Databases = 45
Advisor alert: Databases failed
```

### Mini-Project: Library Management System
The mini-project brings every earlier concept together. Students work in pairs for about four hours and submit code, a class diagram and a short report.

#### Requirements
1. Model `Book`, `Member` and `Loan` classes with `private` fields, validation and `toString`.
2. Create an abstract class `LibraryItem` (`title`, `id`) and subclasses `Book` and `Dvd`, each with its own `loanPeriodDays()` (inheritance, polymorphism, abstraction).
3. Define a `Borrowable` interface with `borrow(Member)` and `giveBack()`.
4. Create custom exceptions: `ItemNotAvailableException` and `MemberLimitExceededException` (maximum 3 loans per member).
5. Store items and members in `Map<String, ...>` collections keyed by `id`.
6. Persist data to CSV files on exit and reload on start (Lab 10).
7. Provide a menu with: add item, register member, borrow, return, list overdue items, search by title (using streams).

#### Suggested class structure
```java
// LibraryItem.java
public abstract class LibraryItem {
    private final String id;
    private final String title;
    private boolean available = true;

    protected LibraryItem(String id, String title) { this.id = id; this.title = title; }

    public abstract int loanPeriodDays();

    public String getId()        { return id; }
    public String getTitle()     { return title; }
    public boolean isAvailable() { return available; }
    void setAvailable(boolean available) { this.available = available; }
}

// Library.java (skeleton of the core service)
public class Library {
    private final Map<String, LibraryItem> items = new HashMap<>();
    private final Map<String, Member> members = new HashMap<>();

    public void borrow(String memberId, String itemId)
            throws ItemNotAvailableException, MemberLimitExceededException {
        // 1. look up member and item (throw IllegalArgumentException if missing)
        // 2. check availability and the member's loan limit
        // 3. create a Loan, mark the item unavailable, record it on the member
    }

    public List<LibraryItem> searchByTitle(String text) {
        return items.values().stream()
            .filter(i -> i.getTitle().toLowerCase().contains(text.toLowerCase()))
            .sorted(Comparator.comparing(LibraryItem::getTitle))
            .toList();
    }
}
```

#### Marking guide (100 marks)

| Criterion | Marks |
| :--- | :--- |
| Correct class design: encapsulation, inheritance, interface, abstraction | 25 |
| Polymorphism demonstrated meaningfully | 10 |
| Custom exceptions and robust error handling | 15 |
| Collections and streams used appropriately | 15 |
| File persistence works across runs | 15 |
| Code quality: naming, comments, no duplication | 10 |
| Report and class diagram | 10 |

---

## Appendix A: Quick Reference

| The four pillars | One-line definition | Main Java tools |
| :--- | :--- | :--- |
| **Encapsulation** | Hide state, expose behaviour | `private`, getters/setters, validation |
| **Inheritance** | Reuse and specialise a parent's code | `extends`, `super`, `protected` |
| **Polymorphism** | One call, many behaviours | Overriding, overloading, interfaces |
| **Abstraction** | Show *what*, hide *how* | `abstract`, `interface` |

### Design guidelines to remember
* Favour **composition over inheritance** unless the relationship is truly "is-a".
* **Program to an interface** (`List`, not `ArrayList`) in declarations and parameters.
* Keep fields `private` and make classes **immutable** whenever possible.
* Give each class **one clear responsibility**.
* Override `equals`, `hashCode` and `toString` together when value equality matters.

---

## Appendix B: Viva Questions
1. What is the difference between a class and an object? Where in memory does each live?
2. Why does Java not allow multiple inheritance of classes, and how do interfaces work around it?
3. Explain the difference between overloading and overriding with an example of each.
4. What happens if a superclass has no no-argument constructor and the subclass omits `super(...)`?
5. When would you choose an abstract class over an interface?
6. Why must `hashCode` be overridden whenever `equals` is overridden?
7. What is the difference between checked and unchecked exceptions? Give two examples of each.
8. Why can generics not be used with primitive types, and what is type erasure?
9. What does `final` mean on a variable, a method and a class?
10. Explain dynamic method dispatch. Why are fields and `static` methods not polymorphic?
11. What is a functional interface, and how does a lambda relate to it?
12. How does `try`-with-resources differ from `try`/`finally`?

---

## Appendix C: Troubleshooting Common Compiler and Runtime Errors

| Message | Usual cause | Fix |
| :--- | :--- | :--- |
| `class X is public, should be declared in a file named X.java` | File name does not match the `public` class | Rename the file or class |
| `cannot find symbol` | Typo, missing import, or variable out of scope | Check spelling, add the import, check where it is declared |
| `incompatible types: possible lossy conversion from double to int` | Assigning a wider type to a narrower one | Cast deliberately: `(int) value` |
| `non-static method cannot be referenced from a static context` | Calling an instance method from `main` without an object | Create an object first |
| `missing return statement` | Some path through a method returns nothing | Return on every path |
| `unreported exception X; must be caught or declared to be thrown` | Checked exception not handled | Add `try`/`catch` or `throws` |
| `Exception in thread "main" java.lang.NullPointerException` | Using a reference that is `null` | Initialise the object or check for `null` |
| `ClassCastException` | Invalid downcast | Check with `instanceof` first |
| `ArrayIndexOutOfBoundsException` | Index outside `0..length-1` | Check loop bounds |
