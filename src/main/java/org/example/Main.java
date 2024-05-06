package org.example;

import entities.*;
import services.Region;
import sets.Address;
import sets.PersonDataRegistry;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public Employee registerNewEmployee(List<Employee> employees) {
    try (Scanner sc = new Scanner(System.in)) {
        System.out.println("Insira o nome do funcionario:");
        String nome = sc.nextLine();
        System.out.println("Insira o telefone do funcionario:");
        String fone = sc.nextLine();
        System.out.println("Insira a idade do funcionario:");
        int idade = sc.nextInt();
        System.out.println("Insira o genero do funcionario: ");
        String sexo = sc.nextLine();
        System.out.println("Insira o email do funcionario:");
        String email =sc.nextLine();
        System.out.println("Insira o CPF do funcionario");
        String cpf = sc.nextLine();
        Address address = new Address().newAddress();
        System.out.println("Agora selecione abaixo o tipo de funcionário: \n\n 1 - Garçom         2 - Balconista          3 - Entregador");
        int choice=sc.nextInt();
        if(choice ==1){
            return new Waiter(nome, fone, idade, sexo, email, cpf, address, employees);
        }
        if(choice==2){
            return new Clerk(nome, fone, idade, sexo, email, cpf, address, employees);
        }
        if(choice==3){
            return new Deliverer(nome, fone, idade, sexo, email, cpf, address, employees);
        }
    }catch (InputMismatchException e) {
        System.out.println("Entrada invalida, por favor tente novamente.");
    }
}

    public void menuAdm(Region localRegion,PersonDataRegistry localRegistry) {
    System.out.println("\n\n            1 - Cadastrar Funcionarios              2 - Relatorios anteriores               3 - Realizar fechamento diario \n\n 4 - Voltar");
    try (Scanner sc = new Scanner(System.in)) {
        int admChoice = sc.nextInt();
            sc.nextLine();

            if (admChoice == 1) {
                registerNewEmployee(localRegistry.getEmployeesList());
                }

            } else if (admChoice == 2) {

            } else if (admChoice == 3) {

            } else {
                System.out.println("Por favor insira uma opção válida. Pressione enter e tente novamente.;");
                sc.nextLine();
            }
        }

    menuPrincipal();
}
    public void menuPrincipal(){
        System.out.println("--Menu Principal--\n1 - Atendimentos\n2 - Cardapio\n3 - Entregas\n4 - Cadastros e Registros\n 5 - Sair");
        Scanner sc = new Scanner(System.in);
        int choice=sc.nextInt();
        sc.nextLine();
        while(choice !=5){
            switch(choice) {
                case 1:
                    menuAttendances();
                case 2:
                    menuMenu();
                case 3:
                    menuDeliveries();
                case 4:
                    menuAdm();
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

    public static void main(String[] args) {
        Region  localRegion = new Region();
        PersonDataRegistry localRegistry = new PersonDataRegistry();
        System.out.println("--Menu Principal--\n        1 - Cadastrar Cliente           2 - Cadastrar Garçom            3 - Cadastrar Mesa\n            4 - Cadastrar Pedido            5 - Visualizar Informações              6 - Sair");
        Scanner sc = new Scanner(System.in);

        int choice = sc.nextInt();
        sc.nextLine();
        while(choice !=6){
            switch(choice){
                case 1:
                    Client client = new Client().registerNewClient();
                    localRegistry.addClientToList(client);
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;

            }

        }
    }
}
