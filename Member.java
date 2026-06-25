

import java.util.ArrayList;
import java.util.List;

public class Member {


    private String           memberId;
    private String           name;
    private int              maxAllowed;
    private List<LibraryItem> borrowedItems;


    private static int nextMemberNumber = 1;


    public Member(String name, int maxAllowed) {


        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Member name cannot be empty.");
        }


        if (maxAllowed <= 0) {
            throw new IllegalArgumentException("maxAllowed must be a positive number.");
        }

        this.name         = name;
        this.maxAllowed   = maxAllowed;
        this.memberId     = "M" + nextMemberNumber;
        nextMemberNumber++;


        this.borrowedItems = new ArrayList<>();
    }


    public String            getMemberId()     { return memberId;     }
    public String            getName()         { return name;         }
    public int               getMaxAllowed()   { return maxAllowed;   }
    public List<LibraryItem> getBorrowedItems(){ return borrowedItems; }


    public int getBorrowedCount() {
        return borrowedItems.size();
    }

    // Can this member borrow one more item?
    public boolean canBorrowMore() {
        return borrowedItems.size() < maxAllowed;
    }


    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Member name cannot be empty.");
        }
        this.name = name;
    }

    public void setMaxAllowed(int maxAllowed) {
        if (maxAllowed <= 0) {
            throw new IllegalArgumentException("maxAllowed must be a positive number.");
        }
        this.maxAllowed = maxAllowed;
    }




    public void addBorrowedItem(LibraryItem item) {
        borrowedItems.add(item);
    }


    public boolean removeBorrowedItem(LibraryItem item) {
        return borrowedItems.remove(item);
    }
}
