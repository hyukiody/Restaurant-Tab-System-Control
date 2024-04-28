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
    public Employee findEmployeeByCpf(String cpf){
        for (Employee employee : employees){
            if(employee.getCpf().equals(cpf)){
                return employee;
            }
        }
        return null;
    }
    public List<Employee> getEmployeesList(){
        return employees;
    }
    public void addEmployeeToList(Employee employee){
        this.employees.add(employee);
    }
    public void removeEmployeeFromList(Employee employee){
        this.employees.remove(employee);
    }
    public void removeEmployeeByCpf(String cpf){
        for(Employee employee : employees){
            if(employee.equals(findEmployeeByCpf(cpf))){
                employees.remove(employee);
            }
        }
    }
    public Client findClientByCpf(String cpf){
        for(Client client : this.clients){
            if(client.getCpf().equals(cpf)){
                return client;
            }else{
                return null;
            }
        }
        return null;
    }
    public List<Client> getClientsList(){
        return this.clients;
    }
    public void addClientToList(Client client){
        this.clients.add(client);
    }
    public void removeClientFromList(Client client){
        this.clients.remove(client);
    }
    public void removeClientByCpf(String cpf){
        for(Client client : clients){
            if(client.equals(findClientByCpf(cpf))){
                clients.remove(client);
            }
        }
    }
    //função save e load
    //save: clone, wipe, then list > obj > toString > BufferedWriter > file, then compare to keep or cancel
    //load: new, then  file > bufferedReader > String > parseObject > object > list; compare to keep or cancel
}
