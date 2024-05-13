package org.example;

import entities.*;
import services.Attendance;
import services.Region;
import sets.Address;
import sets.Menu;
import sets.PersonDataRegistry;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

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

    private static void menuAdm(Region localRegion, PersonDataRegistry localRegistry, Scanner scanner) {

        try {
            int admChoice;

            do {
                do {

                    System.out.println("\n\n            1 - Cadastrar Funcionarios          2 - Exibir funcionarios cadastrados              3 - Relatorios anteriores               4 - Realizar fechamento diario \n\n 5 - Voltar");
                    if (scanner.hasNextInt()) {
                        admChoice = scanner.nextInt();
                    } else {

                        scanner.nextLine(); // Consume any invalid input
                        System.out.println("Entrada invalida. Digite um número (1, 2,  3, 4 ou 5): ");
                        admChoice = scanner.nextInt();
                        continue;
                    }
                    scanner.nextLine();

                } while (admChoice < 1 || admChoice > 5); // validate choice range

                switch (admChoice) {
                    case 1:
                        registerNewEmployee(localRegistry.getEmployeesList(), scanner);
                        viewEmployeesInLocalRegistry(localRegistry);

                        break;
                    case 2:
                        viewEmployeesInLocalRegistry(localRegistry);
                        break;
                    case 3:
                        viewPastDailyReports();
                        //read and display history of attendances saved at pc files
                        break;
                    case 4:
                        // closeDayAttendances(localRegion.getAttendances()){};//PENDING
                        // generating daily reports and saving to local file for further loading
                        break;
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

    private static void viewPastDailyReports() {
    }

    private static void closeDayAttendances(Attendance attendances) {
    }

    private static void viewEmployeesInLocalRegistry(PersonDataRegistry localRegistry) {
        System.out.println(localRegistry.viewEmployeesInRegistry(localRegistry.getEmployeesList()));
    }

    public static void menuPrincipal(Region localRegion, PersonDataRegistry localRegistry, Menu localMenu, Scanner scanner) {

        try {
            int menuChoice;
            do {
                do {
                    System.out.println("--Menu Principal--\n1 - Atendimentos            2 - Cardapio            3 - Entregas            4 - Cadastros e Registros\n            5 - Sair");

                    if (scanner.hasNextInt()) {
                        menuChoice = scanner.nextInt();
                        scanner.nextLine();
                    } else {
                        scanner.nextLine();
                        menuChoice = scanner.nextInt();
                        scanner.nextLine();
                    }
                } while (menuChoice < 1 || menuChoice > 5); // validate choice range


                switch (menuChoice) {
                    case 1:
                        System.out.println("Starting menuAttendances...");
                        menuAttendances();
                        System.out.println("Finished menuAttendances");
                        break;
                    case 2:
                        System.out.println("Starting menuMenu...");
                        menuMenu(localMenu,scanner);
                        System.out.println("Finished menuMenu");
                        break;
                    case 3:
                        System.out.println("Starting menuDeliveries...");
                        menuDeliveries(scanner);
                        System.out.println("Finished menuDeliveries");
                        break;
                    case 4:
                        System.out.println("Starting menuAdm...");
                        menuAdm(localRegion, localRegistry, scanner);
                        System.out.println("Finished menuAdm");
                        break;
                    case 5:
                        System.out.println("Exiting program.");
                        break;

                }
            } while (menuChoice != 5);
        } catch (InputMismatchException e) {
            System.out.println("Entrada invalida. Tente novamente");
            throw new InputMismatchException();
        } catch (Exception e) {
            System.out.println("Um erro ocorreu: 0 " + e.getMessage());
            e.printStackTrace();
        }

    }

    private static void menuAttendances() {
    }

    private static void menuMenu(Menu localMenu, Scanner scanner) {
        int menuMenuChoice;
        try {
            do {
                System.out.println("-- CARDAPIO -- \n       1- EXIBIR CARDAPIO          2- ADICIONAR PRATO AO CARDAPIO          3- REMOVER PRATO DO CARDAPIO            4- EDITAR PRATO DO CARDAPIO");
                menuMenuChoice = scanner.nextInt();
                scanner.nextLine();
            }while(menuMenuChoice<1 || menuMenuChoice>4);

            switch(menuMenuChoice){
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
            }

        } catch (Exception e) {
            System.out.println("Um erro ocorreu 4: "+ e.getMessage());
            e.printStackTrace();
        }
    }

    private static void menuDeliveries(Scanner scanner) {
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Region localRegion = new Region();
        PersonDataRegistry localRegistry = new PersonDataRegistry();
        Menu localMenu = new Menu();
        Scanner sc = new Scanner(System.in);
        try {
            menuPrincipal(localRegion, localRegistry, localMenu, sc);
        } catch (Exception e) {
            System.out.println("Ocorreu um erro 11" + e.getMessage());
            e.printStackTrace();
        }
    }

}
