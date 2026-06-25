# Library Lending System — Java OOP Assignment

A menu-driven console application that manages a library catalog, registers members, and handles borrowing and returning items.

## How to Run

### Requirements
- Java JDK 8 or higher installed on your machine

### Steps

1. Open a terminal (Command Prompt on Windows, Terminal on Mac/Linux)
2. Navigate to the `src` folder:
   ```
   cd src
   ```
3. Compile all files:
   ```
   javac *.java
   ```
4. Run the program:
   ```
   java Main
   ```

The program will start with 3 pre-loaded items and 2 members so you can test right away.

## Pre-loaded Data

| ID     | Item                  | Type     |
|--------|-----------------------|----------|
| ITEM-1 | Clean Code            | Book     |
| ITEM-2 | National Geographic   | Magazine |
| ITEM-3 | Inception             | DVD      |

| ID | Member | Limit |
|----|--------|-------|
| M1 | Alice  | 3     |
| M2 | Bob    | 2     |

## Menu Options

```
===== Library Lending System =====
1. Add Item
2. Add Member
3. Borrow Item
4. Return Item
5. List Catalog
6. Report
7. Search by Title  [Bonus]
8. Show Available Items  [Bonus]
9. Exit
```

## OOP Concepts Used

- **Abstraction** — `LibraryItem` is an abstract class; you cannot create it directly
- **Inheritance** — `Book`, `Magazine`, `DVD` all extend `LibraryItem`
- **Encapsulation** — all fields are private; accessed only through getters/setters
- **Polymorphism** — `listCatalog()` and `borrowItem()` work through the `LibraryItem` type with no instanceof checks
- **Custom Exception** — `LibraryException` handles all rule violations cleanly
- **Collections** — `Map` for fast lookup, `Set` for no-duplicate borrowed ids, `List` for member items

## Bonus Features Implemented

- Search item by title (case-insensitive keyword search)
- Show available items only
- Statistics by item type in the report (counts per type)

## Project Structure

```
src/
├── LibraryItem.java     ← Abstract base class
├── Book.java            ← Subclass: loan = 21 days
├── Magazine.java        ← Subclass: loan = 7 days
├── DVD.java             ← Subclass: loan = 3 days
├── LibraryException.java← Custom exception
├── Member.java          ← Library member
├── Library.java         ← Main engine (borrow, return, report)
└── Main.java            ← Menu and user interaction
```
