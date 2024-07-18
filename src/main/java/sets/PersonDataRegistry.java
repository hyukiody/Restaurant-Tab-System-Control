package sets;

import entities.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PersonDataRegistry {
    private List<Client> clients;
    private List<Employee> employees;
    private String filename = "PersonDataRegistry.txt";

    public PersonDataRegistry() {
        this.clients = new ArrayList<Client>();
        this.employees = new ArrayList<Employee>();
    }


    public Employee getEmployeeById(int id) {
        for (Employee employee :getEmployeesList()) {
            if (employee.getIdAuth()==id) {
                return employee;
            }
        }
        return null;
    }

    public List<Employee> getEmployeesList() {
        return this.employees;
    }

    public void addEmployeeToList(Employee employee) {
        getEmployeesList().add(employee);
    }

    public void removeEmployeeFromList(Employee employee) {
        getEmployeesList().remove(employee);
    }

    public void removeEmployeeById(int id) {
        for (Employee employee : getEmployeesList()) {
            if (employee.equals(getEmployeeById(id))) {
                getEmployeesList().remove(employee);
            }
        }
    }

    public Client findClientByCpf(String cpf) {
        for (Client client : getClientsList()) {
            if (client.getCpf().equals(cpf)) {
                return client;
            } else {
                return null;
            }
        }
        return null;
    }

    public List<Client> getClientsList() {
        return this.clients;
    }

    protected void addClientToList(Client client) {
        getClientsList().add(client);
    }

    private void removeClientFromList(Client client) {
        getClientsList().remove(client);
    }

    private void removeClientByCpf(String cpf) {
        for (Client client : getClientsList()) {
            if (client.equals(findClientByCpf(cpf))) {
                getClientsList().remove(client);
            }
        }
    }

    public String viewClientsInRegistry() {
        StringBuilder body = new StringBuilder("Clientes cadastrados no registro local: \n");
        for (Client client : getClientsList()) {
            body.append(client.toString());
            body.append("\n");
        }
        return body.toString();
    }
public List<Clerk> getClerks() {
    return getEmployeesList().stream()
        .filter(Clerk.class::isInstance)
        .map(Clerk.class::cast)
        .collect(Collectors.toList());
}

public List<Deliverer> getDeliverers() {
    return getEmployeesList().stream()
        .filter(Deliverer.class::isInstance)
        .map(Deliverer.class::cast)
        .collect(Collectors.toList());
}
    public String viewEmployeesByType(String employeeType){
        StringBuilder body = new StringBuilder("Funcionarios do tipo: " +  employeeType + " no cadastrados no sistema");
        if(!getEmployeesList().isEmpty()){
            int num=1;
            for(Employee employee : getEmployeesList()){
                if(employee.getClassName().equals(employeeType)){
                    body.append("\n Nº ").append(num).append(employee.toString());
                    num++;
                }
            }
        }else{
            System.out.println("NÃO HÁ FUNCIONARIOS DESTE TIPO CADASTRADOS AINDA");
        }
        return body.toString();

    }
    public String viewEmployeesInRegistry() {
        StringBuilder body = new StringBuilder("Funcionarios cadastrados no registro local:");
        if (!getEmployeesList().isEmpty()) {
            int num = 1;
            for (Employee employee : getEmployeesList()) {
                body.append("\nCadastro n° - ").append(num).append(employee.toString());
                num++;
            }
        } else {
            System.out.println("Não há cadastros no momento.");
        }
        return body.toString();
    }

    public static void registerNewEmployee(PersonDataRegistry localRegistry, Scanner scanner) {
        try {


            // Read and validate choice (avoiding potential NoSuchElementException)
            int choice2;
            do {
                System.out.println("Escolha abaixo o tipo de funcionário: \n\n 1 - Garçom         2 - Balconista          3 - Entregador\n");
                choice2 = scanner.nextInt();
                scanner.nextLine(); // consume newline after integer input
            } while (choice2 < 1 || choice2 > 3); // validate choice range

            // Collect employee details
            System.out.println("Insira o nome do funcionario:");
            String nome = scanner.nextLine();
            System.out.println("Insira o telefone do funcionario:");
            String fone = scanner.nextLine();
            System.out.println("Insira a idade do funcionario:");
            int idade = scanner.nextInt();
            scanner.nextLine(); // consume newline after integer input
            System.out.println("Insira o genero do funcionario: ");
            String sexo = scanner.nextLine();
            System.out.println("Insira o email do funcionario:");
            String email = scanner.nextLine();
          boolean verifier;
String cpf;
do {
    verifier = false;
    System.out.println("Insira o CPF do funcionario");
    cpf = scanner.nextLine();
    if (!cpf.matches("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$")) {
        System.out.println("CPF incorreto; por favor tente novamente");
        verifier = true;
    } else {
        for(Employee employee : localRegistry.getEmployeesList()){
            if(employee.getCpf().equals(cpf)){
                System.out.println("CPF já encontrado no registro local; por favor tente novamente.");
                verifier = true;
                break;
            }
        }
    }
} while (verifier);
            Address address = new Address().newAddress(scanner);
            System.out.println(address.toString());

            // Create employee object based on choice

            Employee employee;
            switch (choice2) {
                case 1:
                    employee = new Waiter(nome, fone, idade, sexo, email, cpf, address, localRegistry);
                    break;
                case 2:
                    employee = new Clerk(nome, fone, idade, sexo, email, cpf, address, localRegistry);
                    break;
                case 3:
                    employee = new Deliverer(nome, fone, idade, sexo, email, cpf, address, localRegistry);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + choice2);
            }

            // Handle successful registration (if employee is created)

            System.out.println("Novo " + employee.getClassName() + " registrado: " + employee);
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Digite um número (1, 2, ou 3): " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Erro no cadastro: " + e.getMessage()); // More informative message for invalid data
        } catch (Exception e) {
            System.out.println("Um erro ocorreu: 3 " + e.getMessage());
        }
    }
    //função save e load
    //save: clone, wipe, then list > obj > toString > BufferedWriter > file, then compare to keep or cancel
    //load: new, then  file > bufferedReader > String > parseObject > object > list; compare to keep or cancel
}
