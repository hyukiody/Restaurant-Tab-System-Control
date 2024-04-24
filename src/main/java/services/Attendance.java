package services;

import services.accountance.Billing;
import platforms.Table;

import java.time.LocalDateTime;

public class Attendance {

    private Table table;
    private Billing billing;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    public Attendance(){
        this.table = null;
        this.billing = null;
        this.startTime= LocalDateTime.now();
    }
    public Attendance(Table table, Billing billing){
        this.table = table;
        this.billing = billing;
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

    public Attendance beginAttendance(){
        return new Attendance();
    }
    public void endAttendance(){
        if(this.billing.getPaymentStatus()=="Paid"){

        }
    }
    public String toString(){
        return "Atendimento Iniciado em: " + this.startTime.toString() + "\n Mesa:" + this.table.getTableInfo()+"\nNota: " + this.billing.toString();
    }
    public void viewAttendance(){
        System.out.println("Atendimento iniciado em: " + this.startTime.toString() + "\n Mesa:" + this.table.getTableInfo()+"\nNota: " + this.billing.toString());

    }
}
