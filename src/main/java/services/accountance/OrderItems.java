package services.accountance;

import entities.Item;

import java.util.Comparator;

public class OrderItems implements Comparator<OrderItems> {
    private Item item;
    private int quantity;
    public OrderItems(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;}
    public Item getItem() {
        return item;
    }
    public void setItem(Item item) {
        this.item = item;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getItemTotalValue(){
        return this.item.getUnitValue() * this.quantity;
    }
    public int compare(OrderItems orderItem1, OrderItems orderItem2) {
        return orderItem1.getItem().getName().toUpperCase().compareTo(orderItem2.getItem().getName().toUpperCase());
    }
    public String toString(){
        return "Item: " + this.item.getName() + " - Quantity: "  + this.getQuantity();
    }

}
