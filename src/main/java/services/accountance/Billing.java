package services.accountance;

import entities.Employee;
import sets.Attendant;
import entities.Client;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Billing {

    private List<Order> billOrders;
    private Client client;
    private List<Attendant> attendantList;
    private double totalBillValue;
    private LocalDateTime paymentTime;
    private String PaymentStatus;
    private int billId;
    private int billingStatus;

    public Billing(Client client,List<Billing> pastBillings) {
        this.billOrders = new ArrayList<Order>();
        this.attendantList = new ArrayList<Attendant>();
        this.client = client;
        this.totalBillValue = 0;
        this.paymentTime = null;
        this.billingStatus = 0;
        this.billId = generateBillId(pastBillings);
    }
    private int generateBillId(List<Billing> billings){
        boolean exists;
        int id;
        do {
            exists=false;
            id = (int) (Math.random() * 1000000);


            for (Billing billing : billings) {
                if (billing.getBillId() == id) {
                    exists = true;
                }
            }
        } while (exists || id==0);
        return id;

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
   public void removeOrderFromBilling(Scanner scanner){
    System.out.println("POR FAVOR INSIRA O NUMERO DO PEDIDO A REMOVER");
    int index = scanner.nextInt();
    scanner.nextLine();
    if (index >= 1 && index <= billOrders.size()) {
        this.billOrders.remove(index - 1);
        if (this.billOrders.isEmpty()) {
            setPaymentStatus("Paid");
        }
        recalculateTotalBillValue();
    } else {
        throw new IndexOutOfBoundsException("Valor fora do alcance.");
    }
}


private void recalculateTotalBillValue() {
    setTotalBillValue(this.billOrders.stream().mapToDouble(Order::getValorTotalPedido).sum());
}

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String AttendantsListToString() {
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

    public void setAttendantList(List<Attendant> attendants) {
        this.attendantList = attendants;
    }

    public void updateBillAttendantsFromOrders() {
        List<Attendant> newUpdate = new ArrayList<>();
        if (!this.getBillOrders().isEmpty()) {
            for (Order order : this.getBillOrders()) {
                newUpdate.add(order.getAttendant());
            }
            this.setAttendantList(newUpdate);
        } else if (this.getBillOrders().isEmpty()) {
            this.setAttendantList(new ArrayList<>());
        }
    }

    public double getTotalBillValue() {
        return totalBillValue;
    }

    public void setTotalBillValue(double totalBillValue) {
        this.totalBillValue = totalBillValue;
    }
    /*public double getTotalBillValue() {
        return totalBillValue = this.billOrders.stream().mapToDouble(Order::getValorTotalPedido).sum();
    }

     */

    public LocalDateTime getPaymentTime() {
        return paymentTime;
    }

    public void makePayment() {
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
    @Override
    public String toString() {

        if (!billOrders.isEmpty()) {
            int counter = 1;
            StringBuilder body =
                    new StringBuilder(new StringBuilder()
                    .append("Nota Fiscal: ID: ").append(getBillId())
                    .append(", \nClient: ").append(client.getName())
                    .append(", \nAttendants: ").append(AttendantsListToString())
                    .append(", \nValor Total: ").append(getTotalBillValue())
                    .append(", \nHora Gerada: ").append(getPaymentTime())
                    .append("\n Pedidos da Nota: \n").toString());
            for (Order order : billOrders) {
                body.append("-Pedido N°").append(counter + "\n");
                body.append(order.toString());
                counter++;
            }
            return body.toString();
        } else {
            return "Nota Fiscal: ID: " + getBillId() + ", Client: " + client.getName() + ", Attendants: " + AttendantsListToString() + "\n Esta nota não possui pedidos. \n";
        }
    }
    // public Billing

}
