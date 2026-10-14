# Java OOP Labs 1-11 Design

## Intent

Build a complete, viva-ready implementation of the supplied University Object-Oriented Programming Lab Manual. The repository should help a 21-year-old Computer Science student at Harare Institute of Technology demonstrate each exercise independently or run a whole lab in one command. Examples should feel grounded in contemporary Zimbabwean student and business life.

## Scope

- Implement every Basic, Intermediate, and Challenge exercise in Labs 1 through 10.
- Implement the four pattern demonstrations and complete Library Management System in Lab 11.
- Keep each lab self-contained in `lab01/` through `lab11/`, using the default package so `javac *.java` works from that folder.
- Give every domain type its own correctly named public `.java` file.
- Put a `main` method on each primary exercise class so an exercise can be demonstrated independently.
- Add `LabXXRunner.java` and `LabXXTest.java` to every lab. The runner prints clear exercise headings; the test is deterministic and fail-fast.
- Add a Lab 11 console, report, and Mermaid class diagram because the manual includes a menu and report/diagram in its requirements and marking guide.
- Preserve the intent of the existing Lab 1 work and learning notes while completing and cleaning it.

## Audience and Example Style

Use Zimbabwean examples naturally: HIT and Mr Chiworera, Harare/Bulawayo/Mutare, Carlton/Tadiwa/Leo/Tariro/Farai/Rudo/Kudzai, EcoCash/InnBucks, local businesses, and student interests such as technology and entrepreneurship. Monetary examples use ZiG. When conversion is relevant, the fixed educational example rate is `1 USD = 30.25 ZiG`; the code must not imply that it is a live market rate.

Avoid forcing personal names into mechanical examples where that would reduce clarity. Do not include private or sensitive personal information.

## Architecture and Conventions

- Target JDK 17 language features and APIs so the work also compiles on JDK 21+.
- Use only the Java standard library; no build tool or third-party dependency is required.
- Prefer `private` and `final`, validate constructor and method inputs immediately, and expose immutable views or defensive copies.
- Use records for immutable data carriers, pattern matching for `instanceof`, enhanced switch expressions, `Path`/`Files`, streams, and try-with-resources where the lab calls for them.
- Use `// EXPLAIN:` sparingly on the mechanisms a student should be ready to explain in a viva.
- Intentionally invalid examples, such as a subclass omitting a required `super(...)`, appear as commented code with the compiler reason. All checked-in `.java` files must compile.
- Avoid interactive blocking in master runners. Input-based exercise classes provide an interactive path while deterministic runners invoke reusable methods with prepared data.
- File and CSV demonstrations accept caller-provided `Path` values. Tests use temporary directories; demonstrations use lab-local generated names.

## Verification

Each lab is independently compiled into an ignored `.build/labXX` directory and verified with:

1. `LabXXTest`, covering normal behavior, invariants, and the most important failure paths.
2. `LabXXRunner`, covering every exercise in manual order with zero unhandled exceptions.
3. Standalone primary exercise entry points where behavior differs from the master runner.

Generated `.class`, `.txt`, and `.csv` files are ignored and cleaned after verification. The authoritative root README/manual remains in the repository.

## Lab 11 Service Design

`Library` owns map-backed item/member registries and the loan collection. `LibraryItem` is abstract and implements `Borrowable`; `Book` and `Dvd` supply their loan periods. `Member` protects the three-active-loan limit. `Loan` records borrow, due, and optional return dates and can determine overdue state for a supplied date.

The service validates lookups, availability, member limits, returns, and duplicate IDs before changing state. Search is case-insensitive, stream-based, and sorted by title. Persistence stores items, members, and loans in separate CSV files, rejects commas/newlines in persisted scalar fields, reloads state consistently, and derives availability/active-member-loan state from active loans. Tests use fixed dates so overdue behavior is deterministic.

`LibraryConsole` supplies the manual's add/register/borrow/return/overdue/search menu. `Lab11Runner` performs an automated end-to-end scenario, including save and reload, without waiting for keyboard input.

## Git Delivery

- Add repository hygiene, design/plan documents, the authoritative manual, and completed Lab 1 in the Lab 1 baseline commit.
- Commit one lab at a time in order with `feat(labXX): implement <topic> exercises and verification runner`.
- Stage only intended source/documentation files. Existing `.idea/` and `out/` content is ignored rather than deleted.
- Finish with a clean tracked worktree and an eleven-commit lab sequence visible in `git log --oneline` (in addition to the repository's pre-existing history).

## Non-Goals

- No GUI, database, web service, external testing framework, Maven, or Gradle.
- No live currency conversion or claim that the fixed ZiG example rate is current.
- No deliberately uncompilable source file.
- No publication, remote push, or rewriting of pre-existing Git history.
