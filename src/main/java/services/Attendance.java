package services;

import entities.Client;
import services.accountance.Billing;
import platforms.Table;

import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Attendance {

    private Table table;
    private Billing billing;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    public Attendance(Table table, Client client){
        this.table = table;
        this.billing = new Billing(client);
        this.startTime= LocalDateTime.now();
    }
    public LocalDateTime getStartTime() {
        return startTime;
    }
    public Billing getBilling() {
        return billing;
    }
    public void setBilling(Billing billing) {
        this.billing = billing;
    }
    public Table getTable() {
        return table;
    }
    public void setTable(Table table) {
        this.table = table;
    }

        public void endAttendance(){
        if("Paid".equals(this.billing.getPaymentStatus())){

        }else{}
    }
    public String toString(){
        return "Atendimento Iniciado em: " + this.startTime.toString() + "\n Mesa:" + this.table.getTableInfo()+"\nNota: " + this.billing.toString();
    }
    public void viewAttendance(){
        System.out.println("Atendimento iniciado em: " + this.startTime.toString() + "\n Mesa:" + this.table.getTableInfo()+"\nNota: " + this.billing.toString());

    }
    public void menuAttendances(Region region){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\n1 - Iniciar novo atendimento\n2 - Continuar atendimento\n3 - Exibir atendimentos em andamento\n 4 - Encerrar atendimento pagamento\n5 - Exibir atendimentos encerrados hoje ");
        int choice = sc.nextInt();
        if(choice == 1){

            System.out.println("1- Cadastrar novo cliente\n2 - Iniciar sem cadastro\n 3 - Ja possui cadastro");
            int choice2 = sc.nextInt();
            if(choice2 ==1){
                Client client = new Client().registerNewClient();
                Attendance attendance = newAttenNewClient(region, client);
                region.addAttendance(attendance);
                continueAttendance(attendance);
            }
            if(choice2 == 2){}
            if(choice2 == 3){}


        }
        if(choice == 2){
            System.out.println("Por favor insira o numero da mesa que deseja continuar o atendimento: ");
            region.viewOccupiedTables();
            int tableNumber = sc.nextInt();
            Table table = region.getTableByNumber(tableNumber);
            continueAttendance(region.getAttendanceByTable(table));
        }
        if(choice == 3){
            region.viewOpenAttendances();
        }
        else if(choice == 4){}
        else if(choice ==5){}


    }

    private void continueAttendance(Attendance attendance) {
        if(attendance.getTable().getAttendants().isEmpty()){
            System.out.println("Por favor adicione um garçom ao atendimento");
            attendance.getTable().addAttendants();
        }
        System.out.println(attendance.toString());
        System.out.println("1 - Adicionar item ao pedido\n2 - Remover item do pedido\n3 - Encerrar atendimento\n4 - Adicionar garçom ao atendimento\n5 - Remover garçom do atendimento");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        if(choice ==1){
            System.out.println(attendance.getBilling().toString());
        }
        if(choice ==2){

        }
        if(choice==3){

        }
        if(choice==4){

        }
        if(choice ==5){

        }

    }

    private Attendance newAttenNewClient(Region region, Client client) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escolha a mesa disponivel desejada:  \n");
        region.viewAvailableTables();
        try {
            int tableNumber = sc.nextInt();
            if (tableNumber < 1 || tableNumber > 10) { // assuming table numbers are from 1 to 10
                throw new IllegalArgumentException("Numero da mesa invalido. Por favor insira um numero de 1 a 10.");
            }

            Table table = region.getTableByNumber(tableNumber);
            if (table == null || table.getClient() != null){
                throw new IllegalArgumentException("Mesa não disponível.");
            }
            sc.close();
            return new Attendance(table, client);
        } catch (InputMismatchException e) {
            throw new IllegalArgumentException("Entrada inválida. Por favor insira um numero para a mesa.");
        }
    }
}
