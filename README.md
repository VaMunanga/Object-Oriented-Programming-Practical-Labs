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

