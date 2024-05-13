package sets;


import entities.Item;

import java.io.*;
import java.util.*;

import static entities.Item.fromString;

public class Menu {
    private List<Item> itemMenu;
    private String filename = "menu.txt";

    public Menu() {
        this.itemMenu = new ArrayList<Item>();
    }

    public void addItemToMenu( Scanner scanner) {
        System.out.println("Adicionando item ao menu");
        //constructor invocator for the main function
        System.out.println("Digite o nome do item:");
        String name = scanner.nextLine();
        System.out.println("Digite o valor da unidade:");
        double unitValue = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Digite o ID do item:");
        boolean verificator = false;
        int idItem;
        do {
            idItem = scanner.nextInt();
            scanner.nextLine();
            for (Item item : this.itemMenu) {
                if (idItem == item.getIdItem()) {
                    verificator = true;
                    System.out.println("Este ID já pertence a outro prato. Por favor tente novamente.");
                }
            }
        } while (verificator);
        System.out.println("Selecione os dias da semana disponiveis para o item:");
        Item item = new Item(name, unitValue, idItem);
        item.chooseToAddAvailableDays(item, scanner);
        this.getItemMenu().add(item);

        Collections.sort(this.getItemMenu(), Comparator.comparing(Item::getName).thenComparing(Item::getIdItem));
    }

    public void removeItemFromMenu(Scanner scanner) {
        int choice = 0;
        int id;
        do {
            System.out.println("Itens cadastrados no momento");
            this.toString();
            System.out.println("Digite o ID do item que deseja remover:\n");
            id = scanner.nextInt();
            scanner.nextLine();
        }while (choice==1) ;
            for (Item item : itemMenu) {
                if (item.getIdItem() == id) {
                    itemMenu.remove(item);
                    System.out.println("Item removido com sucesso! \nGostaria de remover outro item? \n      1 - Sim     0 - Não");
                    choice = scanner.nextInt();
                    scanner.nextLine();
                }
            }
        }


    public List<Item> getItemMenu() {
        return itemMenu;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CADASTROS DO CARDAPIO: ");
        for (Item item : itemMenu) {
            sb.append(item.toString()).append("\n");
        }
        return sb.toString();
    }

    public void menuMenu() {
        System.out.println("\n\n1-Exibir cardapio\n2 - Adicionar item ao cardapio \n 3 - Remover item do cardapio\n 4 - Editar Item do Cardapio\n ");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        if (choice == 1) {
            System.out.println(this.toString());
            System.out.println("Pressione V para voltar");
            String back = sc.nextLine();
            while (!back.equals("V")) {
                back = sc.nextLine();
            }
            menuMenu();
        } else if (choice == 2) {
        } else if (choice == 3) {
        } else if (choice == 4) {
        }

    }

    public void loadMenu() {
        itemMenu.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                itemMenu.add(fromString(line));

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveMenu() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Item item : itemMenu) {
                writer.write(item.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occured while saving the menu.");
            e.printStackTrace();
        }
    }
}
