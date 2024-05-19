package entities;

import sets.Days;

import java.util.*;

public class Item implements Comparator<Item> {
    private String name;
    private List<String> availableDays;
    private double unitValue;
    private int idItem;

    public Item(String name, double unitValue, int idItem, List<String> availableDays) {
        this.availableDays = availableDays;
        this.name = name;
        this.unitValue = unitValue;
        this.idItem = idItem;
    }

    public Item(String name, double unitValue, int idItem) {
        this.availableDays = new ArrayList<String>();
        this.name = name;
        this.unitValue = unitValue;
        this.idItem = idItem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(double unitValue) {
        this.unitValue = unitValue;
    }

    public List<String> getAvailableDaysList() {
        return availableDays;
    }

    public void setAvailableDaysList(List<String> availableDays) {
        this.availableDays = availableDays;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }
    public static Item fromString(String str) {
        String[] parts = str.split("\n");
        String name = parts[0].split(":")[1].trim();
        List<String> availableDays = Arrays.asList(parts[1].split(":")[1].trim().split(", "));
        double unitValue = Double.parseDouble(parts[2].split(":")[1].trim());
        int idItem = Integer.parseInt(parts[3].split(":")[1].trim());

        Item item = new Item(name, unitValue, idItem);
        item.setAvailableDaysList(availableDays);

        return item;
    }
    @Override
    public String toString() {
        // Dados do item : nome
        // Dias disponiveis: domingo, segunda
        // Valor da unidade: valor
        // ID: id
        return "Dados do item :" + name + "\n" + "Dias disponiveis: " + getToStringAvailableDays() + "\n" + "Valor da unidade=" + unitValue + " \nID =" + idItem;
    }

    public String getToStringAvailableDays() {
        StringBuilder sb = new StringBuilder();
        sb.append("Dias do prato: ");
        for (String day : availableDays) {
            sb.append(day).append(", ");
        }
        return sb.toString();
    }

    public static void chooseToRemoveAvailableDays(Item item, Scanner scanner) {
        //method for existing item
        System.out.println("Dias registrados: ");
        System.out.println(item.getToStringAvailableDays());
        System.out.println("Escolha o dia a ser removido:");
        System.out.println("1- Domingo \n 2- Segunda \n 3- Terça \n 4- Quarta \n 5- Quinta \n 6-Sexta \n 7- Sábado");
        System.out.println("0- Sair");
        int choice = scanner.nextInt();
        scanner.nextLine();

        while (choice != 0) {
            try {
                if (item.availableDays.contains(Days.values()[choice - 1].toString()) == true) {
                    item.availableDays.remove(Days.values()[choice - 1].toString());
                } else {
                    System.out.println("Dia não consta");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Opção inválida");
            }



        }

    }

    public static void chooseToAddAvailableDays(Item item, Scanner scanner) {
        //method for existing item
        System.out.println("Dias registrados: ");
        System.out.println(item.getToStringAvailableDays());
        System.out.println("Escolha o dia a adicionar para o item: ");
        System.out.println("1- Domingo \n 2- Segunda \n 3- Terça \n 4- Quarta \n 5- Quinta \n 6-Sexta \n 7- Sábado");
        System.out.println("0- Sair");
        int choice = scanner.nextInt();
        scanner.nextLine();
        while (choice != 0) {
            try {
                if (!item.availableDays.contains(Days.values()[choice - 1].toString())) {
                    item.availableDays.add(Days.values()[choice - 1].toString());
                } else {
                    System.out.println("Dia já adicionado");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Opção inválida");
            }
        }
    }
    public int compare(Item item1, Item item2) {
        return item1.getName().toUpperCase().compareTo(item2.getName().toUpperCase());
    }

}
