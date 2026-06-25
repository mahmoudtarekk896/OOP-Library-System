
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        preload(library);


        boolean running = true;
        while (running) {
            printMenu();


            int choice = readInt(scanner, "Enter choice: ");


            switch (choice) {

                case 1:
                    addItem(library, scanner);
                    break;

                case 2:
                    addMember(library, scanner);
                    break;

                case 3:
                    borrowItem(library, scanner);
                    break;

                case 4:
                    returnItem(library, scanner);
                    break;

                case 5:
                    library.listCatalog();
                    break;

                case 6:
                    library.printReport();
                    break;

                case 7:
                    System.out.print("Enter search keyword: ");
                    String keyword = scanner.nextLine().trim();
                    library.searchByTitle(keyword);
                    break;

                case 8:
                    library.listAvailable();
                    break;

                case 9:
                    System.out.println("Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a number from the menu.");
            }
        }

        scanner.close();
    }


    private static void printMenu() {
        System.out.println("\n===== Library Lending System =====");
        System.out.println("1. Add Item");
        System.out.println("2. Add Member");
        System.out.println("3. Borrow Item");
        System.out.println("4. Return Item");
        System.out.println("5. List Catalog");
        System.out.println("6. Report");
        System.out.println("7. Search by Title  [Bonus]");
        System.out.println("8. Show Available Items  [Bonus]");
        System.out.println("9. Exit");
    }


    private static void addItem(Library library, Scanner scanner) {
        System.out.println("\nWhat type?  1=Book  2=Magazine  3=DVD");
        int type = readInt(scanner, "Type: ");

        System.out.print("Title: ");
        String title = scanner.nextLine().trim();

        try {
            switch (type) {

                case 1:
                    System.out.print("Author: ");
                    String author = scanner.nextLine().trim();
                    int pages = readInt(scanner, "Pages: ");
                    Book book = new Book(title, author, pages);
                    library.addItem(book);
                    System.out.println("Added book — id: " + book.getId());
                    break;

                case 2:
                    int issue = readInt(scanner, "Issue number: ");
                    Magazine mag = new Magazine(title, issue);
                    library.addItem(mag);
                    System.out.println("Added magazine — id: " + mag.getId());
                    break;

                case 3:
                    int runtime = readInt(scanner, "Runtime (minutes): ");
                    DVD dvd = new DVD(title, runtime);
                    library.addItem(dvd);
                    System.out.println("Added DVD — id: " + dvd.getId());
                    break;

                default:
                    System.out.println("Unknown type — item not added.");
            }

        } catch (IllegalArgumentException e) {

            System.out.println("Error: " + e.getMessage());
        }
    }


    private static void addMember(Library library, Scanner scanner) {
        System.out.print("Member name: ");
        String name = scanner.nextLine().trim();
        int limit = readInt(scanner, "Borrow limit: ");

        try {
            Member m = new Member(name, limit);
            library.addMember(m);
            System.out.println("Added member — id: " + m.getMemberId());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    private static void borrowItem(Library library, Scanner scanner) {
        System.out.print("Member id: ");
        String memberId = scanner.nextLine().trim();
        System.out.print("Item id: ");
        String itemId = scanner.nextLine().trim();

        try {
            library.borrowItem(memberId, itemId);
        } catch (LibraryException e) {
            // LibraryException means a rule was broken — show the message
            System.out.println("Could not borrow: " + e.getMessage());
        }
    }


    private static void returnItem(Library library, Scanner scanner) {
        System.out.print("Member id: ");
        String memberId = scanner.nextLine().trim();
        System.out.print("Item id: ");
        String itemId = scanner.nextLine().trim();

        try {
            library.returnItem(memberId, itemId);
        } catch (LibraryException e) {
            System.out.println("Could not return: " + e.getMessage());
        }
    }



    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }


    private static void preload(Library library) {

        library.addItem(new Book("Clean Code", "Robert C. Martin", 431));
        library.addItem(new Magazine("National Geographic", 215));
        library.addItem(new DVD("Inception", 148));


        library.addMember(new Member("Alice", 3));
        library.addMember(new Member("Bob", 2));

        System.out.println("3 items and 2 members loaded.");
        System.out.println("Items: ITEM-1, ITEM-2, ITEM-3");
        System.out.println("Members: M1 (Alice), M2 (Bob)");
    }
}
