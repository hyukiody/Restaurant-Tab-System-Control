package sets;

import entities.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PersonDataRegistry {
    private List<Client> clients;
    private List<Employee> employees;
    private String filename = "PersonDataRegistry.txt";

    public PersonDataRegistry() {
        this.clients = new ArrayList<Client>();
        this.employees = new ArrayList<Employee>();
    }


    public Employee findEmployeeById(int id) {
        for (Employee employee : employees) {
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
        this.employees.add(employee);
    }

    public void removeEmployeeFromList(Employee employee) {
        this.employees.remove(employee);
    }

    public void removeEmployeeById(int id) {
        for (Employee employee : employees) {
            if (employee.equals(findEmployeeById(id))) {
                employees.remove(employee);
            }
        }
    }

    public Client findClientByCpf(String cpf) {
        for (Client client : this.clients) {
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

    public void addClientToList(Client client) {
        this.clients.add(client);
    }

    public void removeClientFromList(Client client) {
        this.clients.remove(client);
    }

    public void removeClientByCpf(String cpf) {
        for (Client client : clients) {
            if (client.equals(findClientByCpf(cpf))) {
                clients.remove(client);
            }
        }
    }

    public String viewClientsInRegistry(List<Client> clients) {
        StringBuilder body = new StringBuilder("Clientes cadastrados no registro local: \n");
        for (Client client : clients) {
            body.append(client.toString());
            body.append("\n");
        }
        return body.toString();
    }

    public String viewEmployeesInRegistry(List<Employee> employees) {
        StringBuilder body = new StringBuilder("Funcionarios cadastrados no registro local:");
        if (!employees.isEmpty()) {
            int num = 1;
            for (Employee employee : employees) {
                body.append("\nCadastro n° - ").append(num).append(employee.toString());
                num++;
            }
        } else {
            System.out.println("Não há cadastros no momento.");
        }
        return body.toString();
    }

    public static void registerNewEmployee(List<Employee> employees, Scanner scanner) {
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
            System.out.println("Insira o CPF do funcionario");
            String cpf = scanner.nextLine();
            Address address = new Address().newAddress(scanner);
            System.out.println(address.toString());

            // Create employee object based on choice

            Employee employee = switch (choice2) {
                case 1 -> new Waiter(nome, fone, idade, sexo, email, cpf, address, employees);

                case 2 -> new Clerk(nome, fone, idade, sexo, email, cpf, address, employees);
                case 3 -> new Deliverer(nome, fone, idade, sexo, email, cpf, address, employees);
                default -> throw new IllegalStateException("Unexpected value: " + choice2);
            };

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
