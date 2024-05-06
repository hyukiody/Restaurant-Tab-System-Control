package services.accountance;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import platforms.Table;
import sets.Attendant;

public class Order {
    private List<OrderItems> orderItems;
    private Table table;
    private Attendant attendant;
    private double orderValue;
    private LocalDateTime timeOrdered;
    private LocalDateTime timeDelivered;

    public Order() {
    }

    public Order(Table table, Attendant attendant, LocalDateTime timeOrdered, LocalDateTime timeDelivered) {
        this.orderItems = new ArrayList<OrderItems>();
        this.table = table;
        this.attendant = attendant;
        this.timeOrdered = timeOrdered;
    }

    public Order doneOrderToBilling(Table table, Attendant attendant, LocalDateTime timeOrdered, LocalDateTime timeDelivered) {
        return new Order (table, attendant, timeOrdered,timeDelivered);
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

    public Table getMesa() {
        return table;
    }

    public void setMesa(Table table) {
        this.table = table;
    }

    public sets.Attendant getAttendant() {
        return attendant;
    }

    public void setAttendant(Attendant attendant) {
        this.attendant = attendant;
    }

    public double getValorTotalPedido() {
        return orderValue;
    }

    public void setValorTotalPedido(double valorPedido) {
        this.orderValue = this.orderItems.stream().mapToDouble(OrderItems::getItemTotalValue).sum();
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

    public String toString() {

        if (!orderItems.isEmpty()) {
            int counter = 1;
            StringBuilder string = new StringBuilder("--Lista de itens do pedido:\n");
            for (OrderItems orderItems : orderItems) {
                string.append("---Item n°" + counter + "\n");
                string.append(orderItems.toString()).append(",\n ");
                counter++;
            }
            return string.toString();
        } else {
            return "Lista de Pedidos vazia.";
        }
    }

    public void placeOrder() {
        this.timeDelivered = LocalDateTime.now();
    }
}
