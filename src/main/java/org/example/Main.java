package org.example;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public void menuPrincipal(){}
    public void Atendimento(){}

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) {

        System.out.println("--Menu Principal--\n1 - Cadastrar Cliente\n2 - Cadastrar Garçom\n3 - Cadastrar Mesa\n4 - Cadastrar Pedido\n5 - Visualizar Informações\n6 - Sair");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        while(choice!=6){
            switch(choice){
                case 1:

            }

        }
    }
}
