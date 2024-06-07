package services.accountance;

import entities.Address;
import entities.Client;
import sets.Attendant;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ReservationOrder {
    private List<OrderItems> orderItems;
    private Attendant attendant;
    private double orderValue;
    private LocalDateTime timeOrdered;
    private LocalDateTime timeScheduled;
    private LocalDateTime timeDelivered;
    private Address reservationAddress;
    private Client client;

    public ReservationOrder(List<OrderItems> orderItems, sets.Attendant attendant, LocalDateTime timeScheduled, Address reservationAddress, Client client) {
        this.orderItems = new ArrayList<OrderItems>();
        this.attendant = attendant;
        this.orderValue = getTotalOrderValue();
        this.timeOrdered = LocalDateTime.now();
        this.timeScheduled = timeScheduled;
        this.reservationAddress = reservationAddress;
        this.client = client;
    }
    public void addToReservationOrder(OrderItems orderItem){
        getOrderItems().add(orderItem);
    }
    public List<OrderItems> getOrderItems() {
        return orderItems;
    }

    public void adicionarAoPedido(OrderItems orderItems) {
        this.orderItems.add(orderItems);
        this.orderItems.sort(Comparator.comparing(o -> o.getItem().getName()));

    }

    public void removerDoPedido(OrderItems orderItems) {
        this.orderItems.remove(orderItems);
    }

    public sets.Attendant getAttendant() {
        return attendant;
    }

    public void setAttendant(Attendant attendant) {
        this.attendant = attendant;
    }

    public double getTotalOrderValue() {
        refreshTotalOrderValue();
        return this.orderValue;
    }

    public void refreshTotalOrderValue() {
        this.orderValue = getOrderItems().stream().mapToDouble(OrderItems::getItemTotalValue).sum();
    }

    public LocalDateTime getTimeOrdered() {
        return timeOrdered;
    }

    public void setTimeOrdered(LocalDateTime timeOrdered) {
        this.timeOrdered = timeOrdered;
    }

    public LocalDateTime getTimeDelivered() {
        return timeDelivered;
    }

    public void setTimeDelivered(LocalDateTime timeDelivered) {
        this.timeDelivered = timeDelivered;
    }

    @Override
    public String toString() {

        if (!getOrderItems().isEmpty()) {
            int counter = 1;
            StringBuilder string = new StringBuilder("\nInformações da Reserva:\n")
                    .append("Cliente: ").append(getClient())
                    .append("Atendente: ").append(getAttendant())
                    .append("Valor total da reserva: ").append(getTotalOrderValue())
                    .append("Reservado em: ").append(getTimeOrdered().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")))
                    .append("Local de entrega: ").append(getReservationAddress());
            for (OrderItems orderItems : getOrderItems()) {
                string.append("\n--Lista de itens da reserva: \n ---Item n°" + counter + "\n");
                string.append(orderItems.toString()).append(",\n ");
                counter++;
            }
            return string.toString();
        } else {
            return "Lista de Pedidos vazia.";
        }
    }

    public void deliverOrder() {
        this.timeDelivered = LocalDateTime.now();
    }

    public Address getReservationAddress() {
        return reservationAddress;
    }

    public void setReservationAddress(Address reservationAddress) {
        this.reservationAddress = reservationAddress;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDateTime getTimeScheduled() {
        return timeScheduled;
    }

    public void setTimeScheduled(LocalDateTime timeScheduled) {
        this.timeScheduled = timeScheduled;
    }
}
