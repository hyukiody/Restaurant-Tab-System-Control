package services;

import services.accountance.Billing;
import services.accountance.Order;
import platforms.Table;

public class Attendance {

    private Table table;
    private Billing billing;
    private Order order;
    public Attendance(Table table, Billing billing, Order order){
        this.table = table;
        this.billing = billing;
        this.order = order;
    }

    public void newAttendance(){

    }
}
