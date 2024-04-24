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
        String history = "";
        for (Billing bill : pastBillings){
            history += bill.toString();
        }
        return history;
    }
}
