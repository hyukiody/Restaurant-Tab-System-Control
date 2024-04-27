package services.accountance;

import entities.Attendant;
import platforms.Table;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

public class DeliveryOrder {
    private List<OrderItems> orderItems;
    private Table table;
    private Attendant attendant;
    private double orderValue;
    private LocalDateTime timeOrdered;
    private LocalDateTime timeDelivered;

    public DeliveryOrder(List<OrderItems> orderItems, Table table, Attendant attendant, double orderValue, LocalDateTime timeOrdered, LocalDateTime timeDelivered) {
        this.orderItems = orderItems;
        this.table = table;
        this.attendant = attendant;
        this.orderValue = orderValue;
        this.timeOrdered = timeOrdered;
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
    public Attendant getAttendant() {
        return attendant;
    }
    public void setAttendant(Attendant attendant) {
        this.attendant = attendant;
    }
    public double getValorTotalPedido() {
        return orderValue;
    }
    public void setValorTotalPedido(double valorPedido) {
        this.orderValue = this.orderItems.stream().mapToDouble(OrderItems::getItemTotalValue).sum();}
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
    public String toString(){

        if(!orderItems.isEmpty()){
            int counter = 1;
            StringBuilder string = new StringBuilder("--Lista de itens do pedido de Delivery:\n");
            for (OrderItems orderItems : orderItems) {
                string.append("---Item n°" + counter + "\n");
                string.append(orderItems.toString()).append(",\n ");
                counter ++;
            }
            return string.toString();
        }
        else{
            return "Lista de Pedidos vazia.";
        }
    }
    public void deliverOrder(){
        this.timeDelivered = LocalDateTime.now();
    }

}
