package entities;

import java.util.ArrayList;
import java.util.List;
public class Menu {
    private List<Item> cardapio;

public Menu() {
    this.cardapio= new ArrayList<Item>();
    }
    public void addItemAoCardapio(Item item){
        cardapio.add(item);
    }
    public void removeItemDoCardapio(Item item){
    cardapio.remove(item);
    }
    public List<Item> getCardapio() {
        return cardapio;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Menu: ");
        for (Item item : cardapio) {
            sb.append(item.toString()).append(", ");
        }
        return sb.toString();
    }


}
