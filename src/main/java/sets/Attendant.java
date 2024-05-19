package sets;

import java.util.List;

public interface Attendant {
    String getName();

    static void registerNewAttendant(List<Attendant> attendants, Attendant attendant) {
        attendants.add(attendant);
    }

    Attendant getAttendant();

    int getId();
}
