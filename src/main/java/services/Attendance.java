package services;

import accountance.Billing;
import accountance.Order;
import platforms.Table;

import java.util.List;

public class Attendance {

    private Table table;
    private Billing billing;
    private Order order;
    public Attendance(Table table, Billing billing, Order order){
        this.table = table;
        this.billing = billing;
        this.order = order;
    }
    public void viewOngoingAttendances(){
    }
    public void newAttendance(){

    }
}
