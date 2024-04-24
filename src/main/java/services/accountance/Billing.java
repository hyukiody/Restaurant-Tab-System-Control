package services.accountance;

import sets.Employee;
import entities.Client;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class Billing {

    private List<Order> billOrders;
    private Client client;
    private Employee employee;
    private double totalBillValue;
    private LocalDateTime paymentTime;
    private String PaymentStatus;



    private int billId;
    public Billing(){
        this.billOrders = new ArrayList<Order>();
        this.client=null;
        this.employee =null;
        this.totalBillValue =0;
        this.paymentTime =null;
        this.PaymentStatus = "Paid";
        this.billId=(int)Math.random()*1000;
    }
    public Billing(Client client, Employee employee, LocalDateTime paymentTime, String PaymentStatus) {
        this.billOrders = new ArrayList<Order>();
        this.client = client;
        this.employee = employee;
        this.totalBillValue = 0;
        this.paymentTime = paymentTime;
        this.PaymentStatus = PaymentStatus;
        this.billId=(int)Math.random()*1000;
    }

    public List<Order> getBillOrders() {
        return billOrders;
    }
    public void addOrder(Order order) {
        if(this.getPaymentStatus()=="Paid"){
            setPaymentStatus("Pending");
        }
        this.billOrders.add(order);
    }
    public void removeOrder(Order order) {
        this.billOrders.remove(order);
        if(this.billOrders.isEmpty()){
            setPaymentStatus("Paid");
        }
    }
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public Employee getEmployee() {
        return employee;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    public double getTotalBillValue() {
        return totalBillValue = this.billOrders.stream().mapToDouble(Order::getValorTotalPedido).sum();}
    public LocalDateTime getPaymentTime() {
        return paymentTime;
    }
    public void makePayment(LocalDateTime paymentTime) {
        setPaymentStatus("Paid");
        this.paymentTime = LocalDateTime.now();
    }
    public String getPaymentStatus() {
        return PaymentStatus;}
    public void setPaymentStatus(String PaymentStatus) {
        this.PaymentStatus = PaymentStatus;}
    public int getBillId() {
        return billId;
    }
        public String toString(){
        return "Nota Fiscal: ID: "+ getBillId()  + "Client: " + client.getName() + ", Employee: " + employee.getName() + ", Valor Total: " + getTotalBillValue() + ", Hora Gerada: " + getPaymentTime();}

}
