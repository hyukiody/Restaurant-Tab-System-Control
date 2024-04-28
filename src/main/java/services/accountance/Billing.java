package services.accountance;

import sets.Attendant;
import entities.Client;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class Billing {

    private List<Order> billOrders;
    private Client client;
    private List<Attendant> attendantList;
    private double totalBillValue;
    private LocalDateTime paymentTime;
    private String PaymentStatus;
    private int billId;

    public Billing(Client client) {
        this.billOrders = new ArrayList<Order>();
        this.attendantList = new ArrayList<Attendant>();
        this.client = client;
        this.totalBillValue = 0;
        this.paymentTime = null;
        this.PaymentStatus = "Paid";
        this.billId = (int) (Math.random() * 1000);
    }

    public Billing(Client client, LocalDateTime paymentTime, String PaymentStatus) {
        this.billOrders = new ArrayList<Order>();
        this.attendantList = new ArrayList<Attendant>();
        this.client = client;
        this.totalBillValue = 0;
        this.paymentTime = paymentTime;
        this.PaymentStatus = PaymentStatus;
        this.billId = (int) (Math.random() * 1000);
    }

    public List<Order> getBillOrders() {
        return billOrders;
    }


    public void addOrder(Order order) {
        if (this.getPaymentStatus().equals("Paid")) {
            setPaymentStatus("Pending");
        }
        this.billOrders.add(order);
        setTotalBillValue(this.billOrders.stream().mapToDouble(Order::getValorTotalPedido).sum()); //each time an order is added, it recalculates the total value of the bill
    }

    public void removeOrder(Order order) {
        this.billOrders.remove(order);
        if (this.billOrders.isEmpty()) {
            setPaymentStatus("Paid");
        }
        setTotalBillValue(this.billOrders.stream().mapToDouble(Order::getValorTotalPedido).sum()); //each time an order is removed, it recalculates the total value of the bill
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String accessAttendantsList() {
        StringBuilder body = new StringBuilder("List de atendentes: \n");
        for (Attendant attendant : this.attendantList) {
            body.append(attendant.getName() + ", ");
        }
        return body.toString();
    }

    public List<Attendant> getAttendantList() {
        return attendantList;
    }

    public void addAttendant(Attendant attendant) {
        this.attendantList.add(attendant);
    }
    public double getTotalBillValue(){
        return totalBillValue;
    }
    public void setTotalBillValue(double totalBillValue){
        this.totalBillValue = totalBillValue;
    }
    /*public double getTotalBillValue() {
        return totalBillValue = this.billOrders.stream().mapToDouble(Order::getValorTotalPedido).sum();
    }

     */

    public LocalDateTime getPaymentTime() {
        return paymentTime;
    }

    public void makePayment(LocalDateTime paymentTime) {
        setPaymentStatus("Paid");
        this.paymentTime = LocalDateTime.now();
    }

    public String getPaymentStatus() {
        return PaymentStatus;
    }

    public void setPaymentStatus(String PaymentStatus) {
        this.PaymentStatus = PaymentStatus;
    }

    public int getBillId() {
        return billId;
    }

    public String toString() {

        if (!billOrders.isEmpty()) {
            int counter = 1;
            StringBuilder body = new StringBuilder("Nota Fiscal: ID: " + getBillId() + ", Client: " + client.getName() + ", Attendants: " + accessAttendantsList() + ", Valor Total: " + getTotalBillValue() + ", Hora Gerada: " + getPaymentTime() + "\n Pedidos da Nota: \n");
            for (Order order : billOrders) {
                body.append("-Pedido N°").append(counter + "\n");
                body.append(order.toString());
                counter++;
            }
            return body.toString();
        } else {
            return "Nota Fiscal: ID: " + getBillId() + ", Client: " + client.getName() + ", Attendants: " + accessAttendantsList() + "\n Esta nota não possui pedidos. \n";
        }
    }

}
