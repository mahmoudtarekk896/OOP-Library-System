

public class DVD extends LibraryItem {

    private int runtimeMinutes;


    public DVD(String title, int runtimeMinutes) {
        super(title);


        if (runtimeMinutes <= 0) {
            throw new IllegalArgumentException("Runtime must be greater than 0.");
        }
        this.runtimeMinutes = runtimeMinutes;
    }


    public int getRuntimeMinutes() { return runtimeMinutes; }

    public void setRuntimeMinutes(int runtimeMinutes) {
        if (runtimeMinutes <= 0) {
            throw new IllegalArgumentException("Runtime must be greater than 0.");
        }
        this.runtimeMinutes = runtimeMinutes;
    }


    @Override
    public int getLoanPeriodDays() {
        return 3;
    }

    @Override
    public String getType() {
        return "DVD";
    }
}
