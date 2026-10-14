# Java OOP Labs 1-11 Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Implement, verify, and commit every exercise in the Java OOP lab manual from Lab 1 through the Lab 11 Library Management System.

**Architecture:** Each lab is an independent default-package Java program with focused public domain types, standalone exercise entry points, a deterministic test harness, and a master runner. Lab 11 adds a map-backed service, console boundary, and CSV repository; all other labs remain deliberately small and concept-focused.

**Tech Stack:** JDK 17-compatible Java, Java standard library, PowerShell verification commands, Git.

**Spec:** `docs/superpowers/specs/2026-10-14-java-oop-labs-design.md`

## Global Constraints

- Compile with JDK 17 or newer; the current machine exposes only JDK 11, so install/select JDK 21 before the first implementation test.
- Use the default package independently inside each `labXX/` directory.
- Put every public domain type in its own matching `.java` file and add standalone `main` methods to primary exercise classes.
- Use one deterministic `LabXXTest` and one ordered `LabXXRunner` per lab.
- Use strict encapsulation, fail-fast validation, defensive copying, modern Java features, and concise `// EXPLAIN:` comments.
- Use HIT/Harare/business/student examples and ZiG; use `1 USD = 30.25 ZiG` only as a fixed educational example.
- Use only the Java standard library.
- Commit labs sequentially with the exact conventional-commit pattern requested by the user.

## Review Focus

- Boundary values: antique age exactly 25 years, marks 0/100, ages 0/120, and absolute zero must be accepted or rejected exactly as specified; tests belong to Labs 1, 3, and 7.
- Mutable-state leaks: histories, member loans, item collections, and search results must not expose mutable internals; tests belong to Labs 3, 4, and 11.
- Empty collections and bad input: stack underflow, empty word input, malformed CSV, missing IDs, and duplicate IDs must fail predictably; tests belong to Labs 8, 10, and 11.
- File-system isolation: Lab 10 and Lab 11 tests must use temporary paths, work from repository root or lab folders, and clean generated data; tests belong to Labs 10 and 11.
- Transactional borrowing: unavailable items or over-limit members must leave item, member, and loan state unchanged; tests belong to Lab 11.

---

### Task 1: Lab 1 - Classes and Objects

**Files:**
- Create/modify: `.gitignore`, `README.md`, `lab01/Car.java`, `lab01/Rectangle.java`, `lab01/Address.java`, `lab01/Person.java`, `lab01/Main.java`, `lab01/Lab01Runner.java`, `lab01/Lab01Test.java`, `lab01/README.md`
- Create: the design and plan documents named above

**Interfaces:**
- Produces: `Car(String,int,double)`, `display()`, `isAntique()`; `Rectangle(double,double)`, `area()`, `perimeter()`, controlled width mutation for the aliasing exercise; composition through `Person.getAddress()` and a deliberate safe-null demonstration.

- [ ] Write `Lab01Test` assertions for three car values, the 25/26-year antique boundary, rectangle calculations/largest selection, alias visibility, composition, and null handling; compile to confirm missing APIs fail.
- [ ] Implement the four domain files, preserving the manual's observable aliasing while validating dimensions and vehicle data.
- [ ] Implement standalone mains, `Main` as a compatibility delegate, and `Lab01Runner` with five ordered sections and Zimbabwean data.
- [ ] Update the lab record and repository ignore rules for `*.class`, `*.csv`, `*.txt`, `.build/`, `.antigravitycli/`, `.idea/`, `out/`, `.vscode/`, and common IDE metadata.
- [ ] Compile all Lab 1 sources with JDK 17+, run `Lab01Test`, `Lab01Runner`, and primary mains; expect PASS and no unhandled exceptions.
- [ ] Stage only baseline docs/config and `lab01/`; commit `feat(lab01): implement classes and objects exercises and verification runner`.

### Task 2: Lab 2 - Constructors, `this`, and Static Members

**Files:**
- Create: `lab02/Circle.java`, `lab02/Employee.java`, `lab02/InitOrderDemo.java`, `lab02/Temperature.java`, `lab02/Lab02Runner.java`, `lab02/Lab02Test.java`

**Interfaces:**
- Produces: chained circle constructors/count; three chained employee constructors plus independent copy constructor; captured initialization-order trace; Celsius/Fahrenheit factories backed by one private representation.

- [ ] Write failing tests for constructor defaults/validation, counter increments, unique copy IDs, exact static/instance initialization order, and conversion tolerances.
- [ ] Implement the four exercise classes and standalone demonstrations, including `// EXPLAIN:` notes for chaining, class state, and initializer timing.
- [ ] Implement the ordered runner; compile and run test/runner/mains with JDK 17+.
- [ ] Commit `feat(lab02): implement constructors this and static exercises and verification runner`.

### Task 3: Lab 3 - Encapsulation and Access Modifiers

**Files:**
- Create: `lab03/Student.java`, `lab03/BankAccount.java`, `lab03/ImmutablePerson.java`, `lab03/Temperature.java`, `lab03/Lab03Runner.java`, `lab03/Lab03Test.java`

**Interfaces:**
- Produces: validated `Student` and computed grade; balance operations and unmodifiable transaction history; final immutable person; Kelvin-backed conversions.

- [ ] Write failing tests for mark and grade boundaries, invalid financial operations, history contents/mutation rejection, immutable copied collections, and absolute-zero/conversion boundaries.
- [ ] Implement the encapsulated types with constructor/setter invariant reuse and no balance/grade/Kelvin representation leaks.
- [ ] Implement isolated demonstrations and the ordered runner; compile and run all verification entry points.
- [ ] Commit `feat(lab03): implement encapsulation exercises and verification runner`.

### Task 4: Lab 4 - Inheritance and Composition

**Files:**
- Create: `lab04/Person.java`, `lab04/Student.java`, `lab04/Teacher.java`, `lab04/Vehicle.java`, `lab04/Car.java`, `lab04/ElectricCar.java`, `lab04/Animal.java`, `lab04/Dog.java`, `lab04/SafeStack.java`, `lab04/Lab04Runner.java`, `lab04/Lab04Test.java`

**Interfaces:**
- Produces: inherited `display()` reuse; constructor trace from `Vehicle` to `ElectricCar`; compilable explicit-super example plus commented failing form; composed generic stack exposing only stack operations.

- [ ] Write failing tests for inherited state/display output, exact constructor trace, parent-name initialization, LIFO behavior, underflow, and absence of list-only mutation methods from `SafeStack`'s public API.
- [ ] Implement the hierarchies and composition-based stack with defensive snapshot output.
- [ ] Add the commented omitted-`super(...)` compiler example and explanation without checking in invalid Java.
- [ ] Implement demonstrations/runner; compile and verify.
- [ ] Commit `feat(lab04): implement inheritance and composition exercises and verification runner`.

### Task 5: Lab 5 - Polymorphism

**Files:**
- Create: `lab05/Printer.java`, `lab05/Animal.java`, `lab05/Cat.java`, `lab05/Cow.java`, `lab05/Duck.java`, `lab05/Employee.java`, `lab05/Manager.java`, `lab05/Developer.java`, `lab05/Intern.java`, `lab05/BindingParent.java`, `lab05/BindingChild.java`, `lab05/Payment.java`, `lab05/CardPayment.java`, `lab05/MobileMoneyPayment.java`, `lab05/PaymentProcessor.java`, `lab05/Lab05Runner.java`, `lab05/Lab05Test.java`

**Interfaces:**
- Produces: four `Printer.print` overloads; animal `sound()` dispatch; employee `monthlyPay()` hierarchy; instance-method versus field/static binding proof; legacy pattern-matching payment and polymorphic `process()`.

- [ ] Write failing tests for overload formatting, animal sounds, mixed payroll total, binding outputs through parent references, payment subtype validation, and equivalence of legacy/polymorphic results.
- [ ] Implement all types with Zimbabwean payroll and EcoCash/InnBucks examples, keeping subtype logic out of the polymorphic processor path.
- [ ] Implement demonstrations/runner; compile and verify.
- [ ] Commit `feat(lab05): implement polymorphism exercises and verification runner`.

### Task 6: Lab 6 - Abstraction and Interfaces

**Files:**
- Create: `lab06/Shape.java`, `lab06/Circle.java`, `lab06/Rectangle.java`, `lab06/Square.java`, `lab06/Flyable.java`, `lab06/Bird.java`, `lab06/Aeroplane.java`, `lab06/Account.java`, `lab06/SavingsAccount.java`, `lab06/CurrentAccount.java`, `lab06/Student.java`, `lab06/EnglishGreeting.java`, `lab06/ShonaGreeting.java`, `lab06/HITGreeter.java`, `lab06/Lab06Runner.java`, `lab06/Lab06Test.java`

**Interfaces:**
- Produces: polymorphic shape calculations; unrelated `Flyable` implementations; validated account deposit/fee behavior; natural-name and external-marks ordering; explicit diamond-default resolution using both qualified super calls.

- [ ] Write failing tests for shape formulas/validation, fly outputs, deposits/fees, both student orders, and deterministic combined greeting output.
- [ ] Implement abstractions and interface types with immutable dimensions and protected account extension points only where required.
- [ ] Implement demonstrations/runner; compile and verify.
- [ ] Commit `feat(lab06): implement abstraction and interfaces exercises and verification runner`.

### Task 7: Lab 7 - Exception Handling

**Files:**
- Create: `lab07/DivisionUtility.java`, `lab07/InvalidAgeException.java`, `lab07/AgeValidator.java`, `lab07/InsufficientFundsException.java`, `lab07/BankAccount.java`, `lab07/FinallyDemo.java`, `lab07/Lab07Runner.java`, `lab07/Lab07Test.java`

**Interfaces:**
- Produces: safe parse/divide and array access demonstrations; checked age validation; checked withdrawal exception exposing shortfall; return-in-try/finally trace.

- [ ] Write failing tests for numeric/non-numeric/zero division, valid and invalid indices, inclusive age bounds, insufficient-funds shortfall and unchanged balance, and finally-before-return ordering.
- [ ] Implement custom exceptions and utilities so expected failures are handled and demonstrated rather than escaping runners.
- [ ] Implement interactive-safe standalone demonstrations and ordered runner; compile and verify.
- [ ] Commit `feat(lab07): implement exception handling exercises and verification runner`.

### Task 8: Lab 8 - Generics and Collections

**Files:**
- Create: `lab08/CollectionsDemo.java`, `lab08/Stack.java`, `lab08/WordFrequency.java`, `lab08/GenericSorter.java`, `lab08/Lab08Runner.java`, `lab08/Lab08Test.java`

**Interfaces:**
- Produces: integer summary/reversed copy and insertion-order duplicate removal; generic LIFO stack; normalized descending word frequencies with deterministic tie ordering; bounded generic bubble sort.

- [ ] Write failing tests for ten-number results, duplicate order, typed stack/underflow, punctuation/case/empty word counts, integer/string sorts, and already-sorted arrays.
- [ ] Implement collection and generic utilities, including `// EXPLAIN:` comments for wrapper types and type erasure.
- [ ] Implement demonstrations/runner; compile and verify.
- [ ] Commit `feat(lab08): implement generics and collections exercises and verification runner`.

### Task 9: Lab 9 - Nested Types, Enums, Records, and Object Contract

**Files:**
- Create: `lab09/Level.java`, `lab09/Coin.java`, `lab09/StudentRecord.java`, `lab09/Book.java`, `lab09/CustomLinkedList.java`, `lab09/Lab09Runner.java`, `lab09/Lab09Test.java`

**Interfaces:**
- Produces: enhanced-switch advice; immutable record with validated components; ISBN equality/hash code; iterable custom linked list with private static node and inner iterator; greedy coin counts with remainder validation.

- [ ] Write failing tests for every enum branch, record equality/accessors/validation, hash-set ISBN deduplication, empty/non-empty iteration and iterator exhaustion, and coin amounts including 0 and 99.
- [ ] Implement the enum, record, equality contract, and generic iterable types with viva comments on generated record members and equals/hashCode.
- [ ] Implement demonstrations/runner; compile and verify.
- [ ] Commit `feat(lab09): implement nested types enums records and object contract exercises and verification runner`.

### Task 10: Lab 10 - File I/O, Lambdas, and Streams

**Files:**
- Create: `lab10/FileStats.java`, `lab10/FileTextDemo.java`, `lab10/Student.java`, `lab10/Learner.java`, `lab10/GradeBand.java`, `lab10/LearnerRepository.java`, `lab10/StreamExercises.java`, `lab10/Lab10Runner.java`, `lab10/Lab10Test.java`

**Interfaces:**
- Produces: five-line save/read and stats; predicate-based even removal; above-average alphabetical student selection; CSV learner save/load; A/B/C grouping and one report file per band.

- [ ] Write failing tests in temporary directories for exact round-trip text/stats, empty text, even removal, average boundary exclusion/order, learner CSV round-trip, malformed CSV, grade boundaries, grouping, and report contents.
- [ ] Implement NIO operations with explicit UTF-8 and try-with-resources where streams/readers need closing; validate persisted scalar values.
- [ ] Implement a console-aware five-line standalone demo plus deterministic runner using a temporary directory.
- [ ] Compile and run tests/runner/mains; verify no generated lab files remain.
- [ ] Commit `feat(lab10): implement file io lambdas and streams exercises and verification runner`.

### Task 11: Lab 11 - Patterns and Library Management System

**Files:**
- Create: `lab11/AppConfig.java`, `lab11/Notification.java`, `lab11/EmailNotification.java`, `lab11/SmsNotification.java`, `lab11/NotificationFactory.java`, `lab11/DiscountStrategy.java`, `lab11/Checkout.java`, `lab11/GradeListener.java`, `lab11/GradeBook.java`, `lab11/LibraryItem.java`, `lab11/Book.java`, `lab11/Dvd.java`, `lab11/Borrowable.java`, `lab11/Member.java`, `lab11/Loan.java`, `lab11/ItemNotAvailableException.java`, `lab11/MemberLimitExceededException.java`, `lab11/Library.java`, `lab11/LibraryCsvRepository.java`, `lab11/LibraryConsole.java`, `lab11/Lab11Runner.java`, `lab11/Lab11Test.java`, `lab11/README.md`

**Interfaces:**
- Produces: singleton/factory/strategy/observer demonstrations; validated item/member/loan model; map-backed add/register/borrow/return/overdue/search service; three-file CSV save/load; interactive menu; automated end-to-end runner.

- [ ] Write failing tests for singleton identity, factory success/unknown type, strategy swapping, observer fan-out, model invariants, duplicate/missing IDs, three-loan limit, unavailable/return transitions, transactional failures, deterministic overdue/search order, and unmodifiable exposed collections.
- [ ] Write failing temporary-directory persistence tests for books/DVDs/members/active and returned loans, reload consistency, malformed rows, and commas/newlines rejected before save.
- [ ] Implement pattern types and concise standalone demonstrations.
- [ ] Implement models and `Library`, using overloads with supplied `LocalDate` for deterministic tests and `LocalDate.now()` for console convenience.
- [ ] Implement `LibraryCsvRepository` with explicit UTF-8, atomic-in-memory reconstruction before returning a loaded library, and clear checked I/O failures.
- [ ] Implement `LibraryConsole` menu commands and robust input/error handling; implement the automated end-to-end runner without console input.
- [ ] Write `lab11/README.md` with run instructions, design-pattern explanations, report, limitations, and Mermaid class diagram.
- [ ] Compile and run `Lab11Test`, `Lab11Runner`, pattern mains, and a scripted console smoke test; expect PASS and no unhandled exceptions.
- [ ] Commit `feat(lab11): implement design patterns and library management system and verification runner`.

### Task 12: Whole-Repository Verification and Handoff

**Files:**
- Verify only; modify the owning lab and amend only its not-yet-published commit if a defect is found.

**Interfaces:**
- Consumes: every `LabXXTest` and `LabXXRunner`.
- Produces: clean source tree and evidence that all labs compile/run independently.

- [ ] From a clean ignored `.build`, compile each `lab01/*.java` through `lab11/*.java` into distinct output folders with JDK 17+.
- [ ] Run all eleven test harnesses and all eleven master runners; record that every test reports PASS and every runner exits 0.
- [ ] Verify `git status --short`, `git diff --check`, ignored generated artifacts, and `git log --oneline`; ensure no `.class`, generated `.txt`, or generated `.csv` is tracked.
- [ ] Remove the validated `.build` directory using an explicit resolved workspace path and report final commit history and any environment note.
