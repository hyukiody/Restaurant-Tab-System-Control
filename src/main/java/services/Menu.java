package services;


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

    public static void addItemToMenu(Menu menu, Scanner scanner) {
        System.out.println("Adicionando novo item ao menu");
        //constructor invocator for the main function
        System.out.println("Digite o nome do novo item:");
        String name = scanner.nextLine();
        System.out.println("Digite o valor da unidade do novo item:");
        double unitValue = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Digite o ID do novo item:");
        boolean hasID = false;
        int idItem;
        do {
            idItem = scanner.nextInt();
            scanner.nextLine();
            for (Item item : menu.getItemMenu()) {
                if (idItem == item.getIdItem()) {
                    hasID = true;
                    System.out.println("Este ID já pertence a outro prato. Por favor tente novamente.");
                }
            }
        } while (hasID);
        System.out.println("Selecione os dias da semana disponiveis para o novo item:");
        Item item = new Item(name, unitValue, idItem);
        item.chooseToAddAvailableDays(item, scanner);
        menu.getItemMenu().add(item);

        Collections.sort(menu.getItemMenu(), Comparator.comparing(Item::getName).thenComparing(Item::getIdItem));
    }

    public static void removeItemFromMenu(Menu menu, Scanner scanner) {
        int choice = 0;
        int id;
        do {
            System.out.println("Itens cadastrados no momento");
            System.out.println(menu.toString());
            ;
            System.out.println("Digite o ID do item que deseja remover:\n");
            id = scanner.nextInt();
            scanner.nextLine();
            for (Item item : menu.getItemMenu()) {
                if (item.getIdItem() == id) {
                    menu.getItemMenu().remove(item);
                    System.out.println("Item removido com sucesso! \nGostaria de remover outro item? \n      1 - Sim     0 - Não");
                    choice = scanner.nextInt();
                    scanner.nextLine();
                }
            }
        } while (choice == 1);

    }

    public static void editItemFromMenu(Menu menu, Scanner scanner) {
        int idEntry;
        try {
            System.out.println(menu);
            System.out.println("Por favor insira o ID do item a editar: ");
            idEntry = scanner.nextInt();
            boolean itemFound = false;
            for (Item item : menu.getItemMenu()) {
                if (item.getIdItem() == idEntry) {
                    itemFound = true;
                    System.out.println("Digite o novo nome do item:");
                    String name = scanner.nextLine();
                    System.out.println("Digite o novo valor da unidade:");
                    double unitValue = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("Digite o novo ID do item:");
                    int idItem;
                    boolean hasID;
                    do {
                        hasID = false;
                        idItem = scanner.nextInt();
                        scanner.nextLine();
                        for (Item item2 : menu.getItemMenu()) {
                            if (idItem == item2.getIdItem()) {
                                hasID = true;
                                System.out.println("Este ID já pertence a um item. Por favor tente novamente.");
                            }
                        }
                    } while (hasID);
                    item = new Item(name, unitValue, idItem);
                    System.out.println("1- Adicionar os dias disponível         2- Remover dias disponiveis");
                    int editDayChoice = scanner.nextInt();
                    scanner.nextLine();
                    if (editDayChoice == 1) {
                        item.chooseToAddAvailableDays(item, scanner);
                    }
                    if (editDayChoice == 2) {
                        item.chooseToRemoveAvailableDays(item, scanner);
                    } else {
                        System.out.println("Entrada invalida. Abortando...");
                        break;
                    }
                }
            }
            if (!itemFound) {
                System.out.println("Item não encontrado, por favor tente novamente.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Por favor, insira um número válido.");
        } catch (NoSuchElementException e) {
            System.out.println("Nenhuma linha foi encontrada para leitura.");
        } catch (NullPointerException e) {
            System.out.println("Item com o ID fornecido não foi encontrado.");
        }

    }

    public static Item findItemById(int id, Menu menu) {
    try {
        for (Item item : menu.getItemMenu()) {
            if (item.getIdItem() == id) {
                return item;
            }
        }
    } catch (NullPointerException e) {
        System.out.println("Menu is null or does not contain any items.");
    }
    return null; // Return null if no item was found with the given id or an exception occurred
}

    public List<Item> getItemMenu() {
        return this.itemMenu;
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

    public static void menuMenu(Menu menu, Scanner scanner) {
        int menuMenuChoice;
        try {
            do {
                System.out.println("-- CARDAPIO -- \n       1- EXIBIR CARDAPIO          2- ADICIONAR PRATO AO CARDAPIO          3- REMOVER PRATO DO CARDAPIO            4- EDITAR PRATO DO CARDAPIO");
                menuMenuChoice = scanner.nextInt();
                scanner.nextLine();
            } while (menuMenuChoice < 1 || menuMenuChoice > 4);

            switch (menuMenuChoice) {
                case 1:
                    System.out.println(menu);
                    break;
                case 2:
                    addItemToMenu(menu, scanner);
                    break;
                case 3:
                    removeItemFromMenu(menu, scanner);
                    break;
                case 4:
                    editItemFromMenu(menu, scanner);
                    break;
            }

        } catch (Exception e) {
            System.out.println("Um erro ocorreu 4: " + e.getMessage());
            e.printStackTrace();
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
