package sets;

import services.accountance.Billing;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class AttendanceHistory {
    private List<Billing> pastBillings;

    public AttendanceHistory() {
        List<Billing> pastBillings = new ArrayList<Billing>();
    }

    public AttendanceHistory getAttendanceHistory() {
        return this;
    }

    public List<Billing> getPastBillings() {
        return this.pastBillings;
    }

    public void addBilling(Billing billing) {
        this.pastBillings.add(billing);
    }

    @Override
    public String toString() {
        StringBuilder history = new StringBuilder();
        for (Billing bill : pastBillings) {
            history.append(bill.toString());
        }
        return history.toString();
    }
}
