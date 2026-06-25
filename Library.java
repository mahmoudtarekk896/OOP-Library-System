


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Library {


    private Map<String, LibraryItem> catalog;
    private Map<String, Member>      members;
    private Set<String>              borrowedIds;


    public Library() {
        catalog     = new HashMap<>();
        members     = new HashMap<>();
        borrowedIds = new HashSet<>();
    }



    public void addItem(LibraryItem item) {
        catalog.put(item.getId(), item);
    }

    public void addMember(Member m) {
        members.put(m.getMemberId(), m);
    }


    public void borrowItem(String memberId, String itemId) throws LibraryException {


        Member member = members.get(memberId);
        if (member == null) {
            throw new LibraryException("Member id '" + memberId + "' not found.");
        }


        LibraryItem item = catalog.get(itemId);
        if (item == null) {
            throw new LibraryException("Item id '" + itemId + "' not found.");
        }

        if (item.isBorrowed()) {
            throw new LibraryException("Item " + itemId + " is already out.");
        }


        if (!member.canBorrowMore()) {
            throw new LibraryException(
                member.getName() + " has reached the borrowing limit of "
                + member.getMaxAllowed() + " items."
            );
        }


        item.markBorrowed();
        member.addBorrowedItem(item);
        borrowedIds.add(itemId);

        System.out.println("Borrowed " + itemId + " to " + memberId + ".");
    }


    public void returnItem(String memberId, String itemId) throws LibraryException {


        Member member = members.get(memberId);
        if (member == null) {
            throw new LibraryException("Member id '" + memberId + "' not found.");
        }


        LibraryItem item = catalog.get(itemId);
        if (item == null) {
            throw new LibraryException("Item id '" + itemId + "' not found.");
        }


        boolean hadIt = member.removeBorrowedItem(item);
        if (!hadIt) {
            throw new LibraryException(
                member.getName() + " does not have item " + itemId + "."
            );
        }


        borrowedIds.remove(itemId);

        System.out.println("Returned " + itemId + " from " + memberId + ".");
    }


    public void listCatalog() {
        if (catalog.isEmpty()) {
            System.out.println("The catalog is empty.");
            return;
        }
        System.out.println("\n--- Catalog ---");
        for (LibraryItem item : catalog.values()) {
            item.displayInfo();
        }
    }


    public void printReport() {
        System.out.println("\n---------- REPORT ----------");
        System.out.println("Total items   : " + catalog.size());
        System.out.println("Currently out : " + borrowedIds.size());
        System.out.println("Borrowed ids  : " + borrowedIds);


        Map<String, Integer> countByType = new HashMap<>();
        for (LibraryItem item : catalog.values()) {
            String type = item.getType();

            countByType.put(type, countByType.getOrDefault(type, 0) + 1);
        }

        System.out.println("Items by type : " + countByType);
        System.out.println("Total created : " + LibraryItem.getTotalItemsCreated());
        System.out.println("----------------------------\n");
    }


    public void searchByTitle(String keyword) {
        System.out.println("\n--- Search results for: \"" + keyword + "\" ---");
        boolean found = false;
        for (LibraryItem item : catalog.values()) {
            if (item.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                item.displayInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No items found matching \"" + keyword + "\".");
        }
    }


    public void listAvailable() {
        System.out.println("\n--- Available items ---");
        boolean found = false;
        for (LibraryItem item : catalog.values()) {
            if (!item.isBorrowed()) {
                item.displayInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No available items right now.");
        }
    }
}
