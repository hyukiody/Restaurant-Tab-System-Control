package entities;


import java.io.*;
import java.util.*;

import static entities.Item.fromString;

public class Menu {
    private List<Item> menu;
    private String filename = "menu.txt";

    public Menu() {
        this.menu = new ArrayList<Item>();
    }
    public void addItemToMenu() {
        System.out.println("Adicionando item ao menu");
        //constructor invocator for the main function
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o nome do item:");
        String name = sc.nextLine();
        System.out.println("Digite o valor da unidade:");
        double unitValue = sc.nextDouble();
        System.out.println("Digite o ID do item:");
        int idItem = sc.nextInt();
        System.out.println("Selecione os dias da semana disponiveis para o item:");
        Item item = new Item(name, unitValue, idItem);
        item.chooseToAddAvailableDays(item);
        menu.add(item);

        Collections.sort(menu, Comparator.comparing(Item::getName));
        sc.close();
    }
    public void removeItemFromMenu(){
        int choice=1;
        Scanner sc = new Scanner(System.in);
        System.out.println("Itens cadastrados no momento");
        menu.toString();
        System.out.println("Digite o ID do item que deseja remover:\n");
        int id = sc.nextInt();
        while(choice!=0){
            for (Item item : menu) {
                if (item.getIdItem() == id) {
                    menu.remove(item);
                    System.out.println("Item removido com sucesso! \nGostaria de remover outro item? \n      1 - Sim     0 - Não");
                    choice = sc.nextInt();
                }
            }
        }
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
    public void saveMenu(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){
            for (Item item : menu){
                writer.write(item.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occured while saving the menu.");
            e.printStackTrace();
        }
    }
    public void loadMenu(){
        menu.clear();
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
            String line;
            while((line = reader.readLine())!=null){
                menu.add(fromString(line));
                
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
