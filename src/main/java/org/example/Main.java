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
        Employee employee = null;
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Insira o nome do funcionario:");
            String nome = sc.nextLine();
            System.out.println("Insira o telefone do funcionario:");
            String fone = sc.nextLine();
            System.out.println("Insira a idade do funcionario:");
            int idade = sc.nextInt();
            sc.nextLine(); // consume newline left-over
            System.out.println("Insira o genero do funcionario: ");
            String sexo = sc.nextLine();
            System.out.println("Insira o email do funcionario:");
            String email = sc.nextLine();
            System.out.println("Insira o CPF do funcionario");
            String cpf = sc.nextLine();
            Address address = new Address().newAddress();
            while(employee==null){
                System.out.println("Agora selecione abaixo o tipo de funcionário: \n\n 1 - Garçom         2 - Balconista          3 - Entregador");
                int choice = sc.nextInt();
                if (choice == 1) {
                    employee = new Waiter(nome, fone, idade, sexo, email, cpf, address, employees);
                } else if (choice == 2) {
                    employee = new Clerk(nome, fone, idade, sexo, email, cpf, address, employees);
                } else if (choice == 3) {
                    employee = new Deliverer(nome, fone, idade, sexo, email, cpf, address, employees);
                } else {
                    System.out.println("Entrada invalida; tente novamente");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada invalida, por favor tente novamente.");
        }
    }

    private void menuAdm(Region localRegion, PersonDataRegistry localRegistry) {
        System.out.println("\n\n            1 - Cadastrar Funcionarios          2 - Exibir funcionarios cadastrados              3 - Relatorios anteriores               4 - Realizar fechamento diario \n\n 5 - Voltar");
        try (Scanner sc = new Scanner(System.in)) {
            int admChoice = sc.nextInt();
            sc.nextLine();

            if (admChoice == 1) {
                registerNewEmployee(localRegistry.getEmployeesList());
            } else if (admChoice == 2) {
                viewEmployeesInLocalRegistry(localRegistry);
            } else if (admChoice == 3) {
                viewPastDailyReports(){};
                //read and display history of attendances saved at pc files
            } else if (admChoice == 4) {
                closeDayAttendances(localRegion.getAttendances()){};//PENDING
                // generating daily reports and saving to local file for further loading
            } else if (admChoice == 5) {
                menuPrincipal(localRegion, localRegistry);

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
        System.out.println("--Menu Principal--\n1 - Atendimentos\n2 - Cardapio\n3 - Entregas\n4 - Cadastros e Registros\n 5 - Sair");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        sc.nextLine();
        while (choice != 5) {
            switch (choice) {
                case 1:
                    menuAttendances();
                case 2:
                    menuMenu();
                case 3:
                    menuDeliveries();
                case 4:
                    menuAdm(localRegion,localRegistry);
                case 5:
                    break;
            }
        }


        sc.close();
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
        menuPrincipal(localRegion,localRegistry);
    }
}
