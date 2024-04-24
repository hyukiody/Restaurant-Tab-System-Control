package sets;

import entities.Client;
import entities.Employee;

import java.util.List;

public class PersonDataRegistry {
    private List<Client> clients;
    private List<Employee> employees;
    private String filename= "PersonDataRegistry.txt";

    public PersonDataRegistry(){
        this.clients = null;
        this.employees = null;
    }
    public void addClient(Client client){
        this.clients.add(client);
    }
    public void removeCliente(Client client){
        this.clients.remove(client);
    }
    //função save e load
    //save: clone, wipe, then list > obj > toString > BufferedWriter > file, then compare to keep or cancel
    //load: new, then  file > bufferedReader > String > parseObject > object > list; compare to keep or cancel
}
