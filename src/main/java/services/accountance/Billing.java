package services.accountance;

import sets.Attendant;
import entities.Client;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Billing {
    private Attendant reservationAttendant;
    private ReservationOrder reservationOrder;
    private List<Order> billOrders;
    private Client client;
    private List<Attendant> attendantList;
    private double totalBillValue;
    private LocalDateTime paymentTime;
    private String paymentStatus;
    private int billId;
    private LocalDateTime timeGenerated;


    public Billing(List<Billing> pastBillings, ReservationOrder reservationOrder) {
        this.reservationOrder = reservationOrder;
        this.reservationAttendant = reservationOrder.getAttendant();
        this.client = reservationOrder.getClient();
        this.totalBillValue = reservationOrder.getTotalOrderValue();
        this.paymentTime = reservationOrder.getTimeDelivered();
        this.billId = generateBillId(pastBillings);
        this.paymentStatus = "Pending";
        getReservationOrder().setTimeDelivered(LocalDateTime.now());
    }   //billing constructor for reserved Order, to be created and saved right after confirmation of delivery

    public Billing(Client client, List<Billing> pastBillings) {
        this.billOrders = new ArrayList<Order>();
        this.attendantList = new ArrayList<Attendant>();
        this.client = client;
        this.totalBillValue = 0;
        this.paymentTime = null;
        this.billId = generateBillId(pastBillings);
        this.paymentStatus = "Quitado";
        this.timeGenerated = LocalDateTime.now();
    }   //attendance bill constructor

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

    public ReservationOrder getReservationOrder() {
        return this.reservationOrder;
    }

    public void setReservationOrder(ReservationOrder reservationOrder) {
        this.reservationOrder = reservationOrder;
    }

    public Attendant getReservationAttendant() {
        return this.reservationAttendant;
    }

    public void setReservationAttendant(Attendant reservationAttendant) {
        this.reservationAttendant = reservationAttendant;
    }

    public List<Order> getBillOrders() {
        return billOrders;
    }


    public void addOrderToBilling(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }

        if (this.billOrders.add(order)) {
            setTotalBillValue(this.billOrders.stream().mapToDouble(Order::getValorTotalPedido).sum());
            this.getPaymentStatus().equals("Quitado");
            setPaymentStatus("Pendente");
        }

    }

    public void removeOrderFromBilling(Scanner scanner) {
        if (this.billOrders == null) {
            throw new IllegalStateException("Bill orders is not set");
        }
        if (this.billOrders.isEmpty()) {
            System.out.println("Não é possível remover. Lista de pedidos da nota está vazia.");
            return;
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
            refreshTotalBillValue();
        } else {
            throw new IndexOutOfBoundsException("Valor fora do alcance.");
        }
    }


    private void refreshTotalBillValue() {
        if (getBillOrders() == null && getReservationOrder() == null) {
            throw new IllegalStateException("Bill orders is not set");
        } else if (getBillOrders() != null && getReservationOrder() == null) {
            setTotalBillValue(getBillOrders().stream().mapToDouble(Order::getValorTotalPedido).sum());
        } else if (getBillOrders() == null && getReservationOrder() != null) {
            setTotalBillValue(getReservationOrder().getTotalOrderValue());
        }

    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String AttendantsListToString() {
        if (getAttendantList() == null) {
            throw new IllegalStateException("Attendant list is not set");
        }
        StringBuilder body = new StringBuilder("Lista de atendentes: \n");
        for (Attendant attendant : getAttendantList()) {
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

    public void setAttendantList(List<Attendant> attendants) {
        this.attendantList = attendants;
    }

    public void addAttendant(Attendant attendant) {
        this.attendantList.add(attendant);
    }

    public void updateBillAttendantsFromBillOrders() {
        List<Attendant> newUpdate = new ArrayList<Attendant>();
        if (!this.getBillOrders().isEmpty()) {
            for (Order order : this.getBillOrders()) {
                newUpdate.add(order.getAttendant());
            }
            this.setAttendantList(newUpdate);
        }
    }

    public double getTotalBillValue() {
        return totalBillValue;
    }

    public void setTotalBillValue(double totalBillValue) {
        this.totalBillValue = totalBillValue;
    }

    public LocalDateTime getPaymentTime() {
        return paymentTime;
    }

    /*public double getTotalBillValue() {
        return totalBillValue = this.billOrders.stream().mapToDouble(Order::getValorTotalPedido).sum();
    }

     */
    public void setPaymentTime(LocalDateTime localDateTIme) {
        this.paymentTime = localDateTIme;
    }

    public void makePayment() {
        setPaymentStatus("Paid");
        this.paymentTime = LocalDateTime.now();
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public int getBillId() {
        return billId;
    }

    public LocalDateTime getTimeGenerated() {
        return this.timeGenerated;
    }

    @Override
    public String toString() {
        if (getBillOrders().isEmpty() && getReservationOrder() != null) {
            StringBuilder body = new StringBuilder().append("Nota Fiscal de Reserva: ID: ").append(getBillId())
                    .append("\nClient: ").append(getClient().getName())
                    .append("\nAttendant: ").append(getReservationAttendant())
                    .append("\nValor Total: ").append(getTotalBillValue())
                    .append("\nHora Gerada: ").append(getTimeGenerated())
                    .append(getReservationOrder().toString())
                    .append("Status do pagamento: ").append(getPaymentStatus());
            return body.toString();
        } else if (!getBillOrders().isEmpty() && getReservationOrder() == null) {
            int counter = 1;
            StringBuilder body = new StringBuilder()
                    .append("Nota Fiscal: ID: ").append(getBillId())
                    .append(", \nClient: ").append(getClient().getName())
                    .append(", \nAttendants: ").append(AttendantsListToString())
                    .append(", \nValor Total: ").append(getTotalBillValue())
                    .append(", \nHora Gerada: ").append(getPaymentTime())
                    .append("\n Pedidos da Nota: \n");
            for (Order order : getBillOrders()) {
                body.append("-Pedido N°").append(counter + "\n");
                body.append(order.toString());
                counter++;
            }
            body.append("\nStatus de pagamento:").append(getPaymentStatus());
            return body.toString();
        } else {
            return "Nota Fiscal: ID: " + getBillId() + ", Client: " + getClient().getName() + "\n Esta nota não possui pedidos. \n";
        }
    }
}
