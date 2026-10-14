# Lab 11 Report

The project demonstrates Singleton configuration, Factory notifications, Strategy discounts, and Observer grade alerts. The library uses an abstract `LibraryItem`, `Book` and `Dvd` subclasses, the `Borrowable` interface, checked domain exceptions, map-backed lookup, stream search/overdue reports, and CSV persistence.

```mermaid
classDiagram
LibraryItem <|-- Book
LibraryItem <|-- Dvd
Borrowable <|.. LibraryItem
Library o-- Member
Library o-- Loan
Library o-- LibraryItem
Loan --> Member
Loan --> LibraryItem
LibraryCsvRepository --> Library
```

Compile with `javac *.java`; run `java Lab11Test`, `java Lab11Runner`, or `java LibraryConsole`.
