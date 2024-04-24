package services.accountance;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import entities.Waiter;
import platforms.Table;

public class Order {
    private List<OrderItems> orderItems;
    private Table table;
    private Waiter waiter;
    private double orderValue;
    private LocalDateTime timeOrdered;
    private LocalDateTime timeDelivered;
    public Order() {}
    public Order(Table table, Waiter waiter, LocalDateTime timeOrdered, LocalDateTime timeDelivered) {
        this.orderItems = new ArrayList<OrderItems>();
        this.table = table;
        this.waiter = waiter;
        this.timeOrdered = timeOrdered;
        this.timeDelivered = timeDelivered;
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
    public Waiter getWaiter() {
        return waiter;
    }
    public void setWaiter(Waiter waiter) {
        this.waiter = waiter;
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


        String string = "Pedidos: \n";
        for(OrderItems orderItems : orderItems){
            string += orderItems.toString() + ", ";
        }
        return string;
    }
}
