package org.example;

import entities.*;
import services.Attendance;
import services.Region;
import sets.Address;
import sets.PersonDataRegistry;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public void registerNewEmployee(List<Employee> employees) {
        try (Scanner sc = new Scanner(System.in)) {

            System.out.println("Escolha abaixo o tipo de funcionário: \n\n 1 - Garçom         2 - Balconista          3 - Entregador\n");

            // Read and validate choice (avoiding potential NoSuchElementException)
            int choice2;
            do {
                while (!sc.hasNextInt()) {
                    System.out.println("Entrada inválida. Digite um número (1, 2, ou 3): ");
                    sc.nextLine(); // consume invalid input
                }
                choice2 = sc.nextInt();
                sc.nextLine(); // consume newline after integer input
            } while (choice2 < 1 || choice2 > 3); // validate choice range

            // Collect employee details
            System.out.println("Insira o nome do funcionario:");
            String nome = sc.nextLine();
            System.out.println("Insira o telefone do funcionario:");
            String fone = sc.nextLine();
            System.out.println("Insira a idade do funcionario:");
            int idade = sc.nextInt();
            sc.nextLine(); // consume newline after integer input
            System.out.println("Insira o genero do funcionario: ");
            String sexo = sc.nextLine();
            System.out.println("Insira o email do funcionario:");
            String email = sc.nextLine();
            System.out.println("Insira o CPF do funcionario");
            String cpf = sc.nextLine();
            Address address = new Address().newAddress();
            System.out.println(address.toString());

            // Create employee object based on choice

            Employee employee = switch (choice2) {
                case 1 -> new Waiter(nome, fone, idade, sexo, email, cpf, address, employees);
                case 2 -> new Clerk(nome, fone, idade, sexo, email, cpf, address, employees);
                case 3 -> new Deliverer(nome, fone, idade, sexo, email, cpf, address, employees);
                default -> throw new IllegalStateException("Unexpected value: " + choice2);
            };

            // Handle successful registration (if employee is created)
            System.out.println("Novo " + employee.getClassName() + " registrado: " + employee.toString());
            return;
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Digite um número (1, 2, ou 3): " + e.getMessage());
        }
    }

    private void menuAdm(Region localRegion, PersonDataRegistry localRegistry) {
        System.out.println("\n\n            1 - Cadastrar Funcionarios          2 - Exibir funcionarios cadastrados              3 - Relatorios anteriores               4 - Realizar fechamento diario \n\n 5 - Voltar");
        try (Scanner sc = new Scanner(System.in)) {
            int admChoice = sc.nextInt();

            if (admChoice == 1) {
                registerNewEmployee(localRegistry.getEmployeesList());
            } else if (admChoice == 2) {
                viewEmployeesInLocalRegistry(localRegistry);
                menuAdm(localRegion, localRegistry);
            } else if (admChoice == 3) {
                viewPastDailyReports();
                //read and display history of attendances saved at pc files
            } else if (admChoice == 4) {
                // closeDayAttendances(localRegion.getAttendances()){};//PENDING
                // generating daily reports and saving to local file for further loading
            } else if (admChoice == 5) {
                return;
            }
        }
    }

    private void viewPastDailyReports() {
    }

    private void closeDayAttendances(Attendance attendances) {
    }

    private void viewEmployeesInLocalRegistry(PersonDataRegistry localRegistry) {
        System.out.println(localRegistry.viewEmployeesInRegistry(localRegistry.getEmployeesList()));
    }

    public void menuPrincipal(Region localRegion, PersonDataRegistry localRegistry) {

        try (Scanner sc = new Scanner(System.in)) {
            int choice;
            do {
                System.out.println("--Menu Principal--\n1 - Atendimentos\n2 - Cardapio\n3 - Entregas\n4 - Cadastros e Registros\n 5 - Sair");
                choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1:
                        menuAttendances();
                        break;
                    case 2:
                        menuMenu();
                        break;
                    case 3:
                        menuDeliveries();
                        break;
                    case 4:
                        menuAdm(localRegion, localRegistry);
                        break;
                    case 5:
                        break;
                    default:
                        System.out.println("Escolha invalida. Tente novamente.");
                }
            }while (choice != 5);
        } catch (InputMismatchException e) {
            System.out.println("Entrada invalida. Tente novamente");
        } catch (Exception e) {
            System.out.println("Um erro ocorreu: " + e.getMessage());
        }
    }

    private void menuAttendances() {
    }

    private void menuMenu() {
    }

    private void menuDeliveries() {
    }

    public void main(String[] args) {
        Region localRegion = new Region();
        PersonDataRegistry localRegistry = new PersonDataRegistry();

        menuPrincipal(localRegion, localRegistry);
    }
}
