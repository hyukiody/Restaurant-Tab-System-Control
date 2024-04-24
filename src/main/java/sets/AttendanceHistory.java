package sets;

import services.accountance.Billing;

import java.util.List;
import java.util.ArrayList;

public class AttendanceHistory {
    private List<Billing> pastBillings;

    public AttendanceHistory(){
        List<Billing> pastBillings = new ArrayList<Billing>();
    }
    public AttendanceHistory getAttendanceHistory() {
        return this;
    }
    public void addBilling(Billing billing) {
        this.pastBillings.add(billing);
    }
    public String toString(){
        StringBuilder history = new StringBuilder();
        for (Billing bill : pastBillings){
            history.append(bill.toString());
        }
        return history.toString();
    }
}
