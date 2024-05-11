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
        try (Scanner scReg = new Scanner(System.in)) {

            System.out.println("Escolha abaixo o tipo de funcionário: \n\n 1 - Garçom         2 - Balconista          3 - Entregador\n");

            // Read and validate choice (avoiding potential NoSuchElementException)
            int choice2;
            do {
                while (!scReg.hasNextInt()) {
                    System.out.println("Entrada inválida. Digite um número (1, 2, ou 3): ");
                    scReg.nextLine(); // consume invalid input
                }
                choice2 = scReg.nextInt();
                scReg.nextLine(); // consume newline after integer input
            } while (choice2 < 1 || choice2 > 3); // validate choice range

            // Collect employee details
            System.out.println("Insira o nome do funcionario:");
            String nome = scReg.nextLine();
            System.out.println("Insira o telefone do funcionario:");
            String fone = scReg.nextLine();
            System.out.println("Insira a idade do funcionario:");
            int idade = scReg.nextInt();
            scReg.nextLine(); // consume newline after integer input
            System.out.println("Insira o genero do funcionario: ");
            String sexo = scReg.nextLine();
            System.out.println("Insira o email do funcionario:");
            String email = scReg.nextLine();
            System.out.println("Insira o CPF do funcionario");
            String cpf = scReg.nextLine();
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

        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Digite um número (1, 2, ou 3): " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Erro no cadastro: " + e.getMessage()); // More informative message for invalid data
        } catch (Exception e) {
            System.out.println("Um erro ocorreu: 3 " + e.getMessage());
        }
    }

    private void menuAdm(Region localRegion, PersonDataRegistry localRegistry) {
        try (Scanner scAdm = new Scanner(System.in)) {

            int admChoice;
            do {
                System.out.println("\n\n            1 - Cadastrar Funcionarios          2 - Exibir funcionarios cadastrados              3 - Relatorios anteriores               4 - Realizar fechamento diario \n\n 5 - Voltar");

                do {
                    while (!scAdm.hasNextInt()) {
                        System.out.println("Entrada inválida. Digite um número (1, 2, ou 3): ");
                        scAdm.nextLine(); // consume invalid input
                    }
                    admChoice = scAdm.nextInt();
                    scAdm.nextLine(); // consume newline after integer input
                } while (admChoice < 1 || admChoice > 5); // validate choice range

                switch (admChoice) {
                    case 1:
                        registerNewEmployee(localRegistry.getEmployeesList());
                        viewEmployeesInLocalRegistry(localRegistry);

                        break;
                    case 2:
                        viewEmployeesInLocalRegistry(localRegistry);
                        break;
                    case 3:
                        viewPastDailyReports();
                        //read and display history of attendances saved at pc files
                    case 4:
                        // closeDayAttendances(localRegion.getAttendances()){};//PENDING
                        // generating daily reports and saving to local file for further loading
                    case 5:
                        break;
                    default:
                        System.out.println("Escolha invalida. Por favor insira opção válida");
                }
            } while (admChoice != 5);
        } catch (InputMismatchException e) {
            System.out.println("Entrada invalida. Por favor tente novamente");
             e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Um erro ocorreu 1: " + e.getMessage());
             e.printStackTrace();
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
        try (Scanner scMenu = new Scanner(System.in)) {
            int menuChoice;
            do {
                System.out.println("--Menu Principal--\n1 - Atendimentos            2 - Cardapio            3 - Entregas            4 - Cadastros e Registros\n            5 - Sair");

                // Bounded integer input validation loop
                do {
                    while (!scMenu.hasNextInt()) {
                        System.out.println("Entrada inválida. Digite um número (1, 2, 3, 4, ou 5): ");
                        scMenu.nextLine(); // consume invalid input
                    }
                    menuChoice = scMenu.nextInt();
                    scMenu.nextLine(); // consume newline after integer input
                } while (menuChoice < 1 || menuChoice > 5); // validate choice range

                switch (menuChoice) {
                    case 1:
                        System.out.println("Starting menuAttendances...");
                        menuAttendances();
                        System.out.println("Finished menuAttendances");
                        break;
                    case 2:
                        System.out.println("Starting menuMenu...");
                        menuMenu();
                        System.out.println("Finished menuMenu");
                        break;

                    case 3:
                        System.out.println("Starting menuDeliveries...");
                        menuDeliveries();
                        System.out.println("Finished menuDeliveries");
                        break;
                    case 4:
                        System.out.println("Starting menuAdm...");
                        menuAdm(localRegion, localRegistry);
                        System.out.println("Finished menuAdm");
                        break;
                    case 5:
                        break;
                    default:
                        System.out.println("Escolha invalida. Tente novamente.");
                }
            } while (menuChoice != 5);

        } catch (InputMismatchException e) {
            System.out.println("Entrada invalida. Tente novamente");
            throw new InputMismatchException();
        } catch (Exception e) {
            System.out.println("Um erro ocorreu: 0 " + e.getMessage());
            throw new RuntimeException();
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
