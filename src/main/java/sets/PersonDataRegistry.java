package sets;

import entities.Client;
import entities.Employee;

import java.util.List;

public class PersonDataRegistry {
    private static PersonDataRegistry instance = null;
    private List<Client> clients;
    private List<Employee> employees;
    private String filename= "PersonDataRegistry.txt";

    public PersonDataRegistry(){
        this.clients = null;
        this.employees = null;
    }
    public static PersonDataRegistry getInstance(){
        if(instance==null){
            instance = new PersonDataRegistry();
        }
        return instance;
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
    public String viewClientsInRegistry(List<Client> clients){
    StringBuilder body = new StringBuilder("Clientes cadastrados no registro local: \n" );
    for(Client client : clients){
        body.append(client.toString());
        body.append("\n");
    }
    return body.toString();
}
public String viewEmployeesInRegistry(List<Employee> employees){
        StringBuilder body = new StringBuilder("Funcionarios cadastrados no registro local:");
        if(!employees.isEmpty()){
            int num =1;
            for(Employee employee : employees){
                body.append("\nCadastro n° ").append(num).append(employee.toString());
                num++;
            }
        }
        else{
            System.out.println("Não há cadastros no momento.");
        }
        return body.toString();
}
    //função save e load
    //save: clone, wipe, then list > obj > toString > BufferedWriter > file, then compare to keep or cancel
    //load: new, then  file > bufferedReader > String > parseObject > object > list; compare to keep or cancel
}
