package sets;

import services.accountance.Billing;

import java.util.List;
import java.util.ArrayList;

public class BillingHistory {
    private List<Billing> pastBillings;

    public BillingHistory() {
       this.pastBillings = new ArrayList<Billing>();
    }

    public BillingHistory getBillingHistory() {
        return this;
    }

    public List<Billing> getPastBillings() {
        return this.pastBillings;
    }

    public void addEndedBilling(Billing billing) {
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
