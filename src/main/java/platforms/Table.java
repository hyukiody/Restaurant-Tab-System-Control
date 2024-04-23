package platforms;

import accountance.Billing;
import entities.Client;
import entities.Waiter;

import java.util.List;

public class Table {
    private Billing billing;
    private Client client;
    private List<Waiter> waiters;

    public Table(){}

    public Table(Billing billing, Client client, List<Waiter> waiters) {
        this.billing = billing;
        this.client = client;
        this.waiters = waiters;
    }
    public void setNotaFiscal(Billing billing) {
        this.billing = billing;
    }
    public Billing getNotaFiscal() {
        return billing;
    }
    public Client getCliente() {
        return client;
    }
    public void setCliente(Client client) {
        this.client = client;
    }
    public List<Waiter> getWaiters() {
        return waiters;
    }
    public void setWaiters(List<Waiter> waiters) {
        this.waiters = waiters;
    }
    public Billing geraNotaFiscal(){
        return this.billing;
    }
}
