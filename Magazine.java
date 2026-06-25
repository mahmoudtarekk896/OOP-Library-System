

public class Magazine extends LibraryItem {

    private int issueNumber;


    public Magazine(String title, int issueNumber) {
        super(title);
        this.issueNumber = issueNumber;
    }


    public int getIssueNumber() { return issueNumber; }

    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
    }


    @Override
    public int getLoanPeriodDays() {
        return 7;
    }

    @Override
    public String getType() {
        return "Magazine";
    }
}
