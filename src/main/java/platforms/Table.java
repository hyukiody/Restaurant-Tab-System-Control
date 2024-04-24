package platforms;

import services.accountance.Billing;
import entities.Client;
import entities.Waiter;

import java.util.List;

public class Table {
    private Client client;
    private List<Waiter> waiters;
    public Table() {
        this.client = null;
        this.waiters = null;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    public Client getClient() {
        return client;
    }
    public void addCliente(Client client){
        this.client = client;
    }
    public void removeCliente(){
        this.client = null;
    }
    public List<Waiter> getWaiters() {
        return waiters;
    }
    public void setWaiters(List<Waiter> waiters) {
        this.waiters = waiters;
    }
    public void addWaiter(Waiter waiter){
        this.waiters.add(waiter);
    }
    public void removeWaiter(Waiter waiter){
        if(this.waiters.contains(waiter)){
        this.waiters.remove(waiter);
        }else{
            System.out.println("Garçom não encontrado");
        }
    }
}
