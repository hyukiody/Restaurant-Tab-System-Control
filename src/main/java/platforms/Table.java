package platforms;

import entities.Client;
import entities.Attendant;

import java.util.List;

public class Table {
    private Client client;
    private List<Attendant> attendants;
    public Table() {
        this.client = null;
        this.attendants = null;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void addClient(Client client){
        this.client = client;
    }
    public void removeClient(){
        this.client = null;
    }
    public List<Attendant> getWaiters() {
        return attendants;
    }
    public void setWaiters(List<Attendant> attendants) {
        this.attendants = attendants;
    }
    public void addWaiter(Attendant attendant){
        if(!this.attendants.contains(attendant)){
            this.attendants.add(attendant);}
        else{
            System.out.println("Garçom já adicionado");
        }
    }
    public void removeWaiter(Attendant attendant){
        if(this.attendants.contains(attendant)){
            this.attendants.remove(attendant);
        }else{
            System.out.println("Garçom não encontrado");
        }
    }
    public void viewTableInfo(){
        System.out.println("Informações da Mesa: \nCliente da mesa: " + this.client.getName() + "\nGarçons da mesa: " + waitersListToString() );;
    }
    public String waitersListToString(){
        String waiters = "";
        for(Attendant attendant : this.attendants){
            waiters += attendant.getName() + ", ";
        }
        return waiters;
    }
    public String getTableInfo(){
        return "Cliente da mesa: " + this.client.getName() + "\nGarçons da mesa: " + waitersListToString();
    }
}
