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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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
        if(!this.waiters.contains(waiter)){
            this.waiters.add(waiter);}
        else{
            System.out.println("Garçom já adicionado");
        }
    }
    public void removeWaiter(Waiter waiter){
        if(this.waiters.contains(waiter)){
            this.waiters.remove(waiter);
        }else{
            System.out.println("Garçom não encontrado");
        }
    }
    public void viewTableInfo(){
        System.out.println("Informações da Mesa: \nCliente da mesa: " + this.client.getName() + "\nGarçons da mesa: " + waitersListToString() );;
    }
    public String waitersListToString(){
        String waiters = "";
        for(Waiter waiter : this.waiters){
            waiters += waiter.getName() + ", ";
        }
        return waiters;
    }
    public String getTableInfo(){
        return "Cliente da mesa: " + this.client.getName() + "\nGarçons da mesa: " + waitersListToString();
    }
}
