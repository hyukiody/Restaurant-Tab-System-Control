package org.example;

import services.Region;
import services.Attendance;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {



    public void menuAdm(){
        System.out.println("\n\n 1 - Cadastrar Funcionarios \n 2 - Relatorios anteriores \n 3 - Realizar fechamento diario");


        menuPrincipal();
    }
    public void menuPrincipal(){
        System.out.println("--Menu Principal--\n1 - Atendimentos\n2 - Cardapio\n3 - Entregas\n4 - Cadastros e Registros\n 5 - Sair");
        Scanner sc = new Scanner(System.in);
        int choice=sc.nextInt();
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

    public static void main(String[] args) {
        Region  localRegion = new Region();

        System.out.println("--Menu Principal--\n1 - Cadastrar Cliente\n2 - Cadastrar Garçom\n3 - Cadastrar Mesa\n4 - Cadastrar Pedido\n5 - Visualizar Informações\n6 - Sair");
        Scanner sc = new Scanner(System.in);

        int choice = sc.nextInt();
        while(choice !=6){
            switch(choice){
                case 1:
                    break;
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
