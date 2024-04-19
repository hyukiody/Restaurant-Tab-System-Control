package contabilidade;

import entities.Item;

public class ItemDoPedido {
    private Item item;
    private int quantidade;

    public ItemDoPedido(Item item, int quantidade) {
        this.item = item;
        this.quantidade = quantidade;
    }
    public Item getItem() {
        return item;
    }
    public void setItem(Item item) {
        this.item = item;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public double getValorTotalItem(){
        return this.item.getValorUnitario() * this.quantidade;
    }
}
