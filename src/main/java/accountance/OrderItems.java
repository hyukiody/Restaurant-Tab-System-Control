package accountance;

import entities.Item;

public class OrderItems {
    private Item item;
    private int quantity;

    public OrderItems(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }
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
        return this.item.getSingleValue() * this.quantity;
    }
}
