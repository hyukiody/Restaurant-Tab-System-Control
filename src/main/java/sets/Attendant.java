package sets;

public interface Attendant {
    public void performOrder();
    public void scheduleOrder();
    public void deliverOrder();

    public String getName();

    public Attendant registerNewAttendant();
    public Attendant getAttendant();
}
