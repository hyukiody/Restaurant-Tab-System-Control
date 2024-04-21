package entities;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<Item> menu;

    public Menu() {
        this.menu = new ArrayList<Item>();
    }



    public void addItemAoCardapio(Item item) {
        menu.add(item);
    }

    public void removeItemFromMenu(Item item) {
        menu.remove(item);
    }

    public List<Item> getMenu() {
        return menu;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Menu: ");
        for (Item item : menu) {
            sb.append(item.toString()).append("\n");
        }
        return sb.toString();
    }


}
