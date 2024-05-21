package services.accountance;

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

    public Billing(Client client, List<Billing> pastBillings) {
        this.billOrders = new ArrayList<Order>();
        this.attendantList = new ArrayList<Attendant>();
        this.client = client;
        this.totalBillValue = 0;
        this.paymentTime = null;
        this.billId = generateBillId(pastBillings);
    }

    private int generateBillId(List<Billing> pastBillings) {
        boolean exists;
        int id;
        do {
            exists = false;
            id = (int) (Math.random() * 1000000);


            for (Billing billing : pastBillings) {
                if (billing.getBillId() == id) {
                    exists = true;
                }
            }
        } while (exists || id == 0);
        return id;

    }

    public List<Order> getBillOrders() {
        return billOrders;
    }


    public void addOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        if (this.getPaymentStatus() == null) {
            throw new IllegalStateException("Payment status is not set");
        }
        if (this.billOrders == null) {
            this.billOrders = new ArrayList<>();
        }
        if (this.getPaymentStatus().equals("Paid")) {
            setPaymentStatus("Pending");
        }
        this.billOrders.add(order);
        setTotalBillValue(this.billOrders.stream().mapToDouble(Order::getValorTotalPedido).sum());
    }

    public void removeOrderFromBilling(Scanner scanner) {
        if (this.billOrders == null) {
            throw new IllegalStateException("Bill orders is not set");
        }
        System.out.println("POR FAVOR INSIRA O NUMERO DO PEDIDO A REMOVER");
        if (!scanner.hasNextInt()) {
            throw new IllegalArgumentException("Input must be an integer");
        }
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
        if (this.billOrders == null) {
            throw new IllegalStateException("Bill orders is not set");
        }
        setTotalBillValue(this.billOrders.stream().mapToDouble(Order::getValorTotalPedido).sum());
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String AttendantsListToString() {
        if (this.attendantList == null) {
            throw new IllegalStateException("Attendant list is not set");
        }
        StringBuilder body = new StringBuilder("Lista de atendentes: \n");
        for (Attendant attendant : this.attendantList) {
            if (attendant == null || attendant.getName() == null) {
                throw new IllegalArgumentException("Attendant or attendant name cannot be null");
            }
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
    public void setPaymentTime(LocalDateTime localDateTIme) {
        this.paymentTime = localDateTIme;
    }

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
            return "Nota Fiscal: ID: " + getBillId() + ", Client: " + client.getName() + "\n Esta nota não possui pedidos. \n";
        }
    }
}
