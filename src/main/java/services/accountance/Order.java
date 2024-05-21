package services.accountance;

import java.time.LocalDateTime;
import java.util.*;

import platforms.Attendance;
import platforms.Table;
import services.Menu;
import sets.Attendant;
import sets.PersonDataRegistry;

public class Order {
    private List<OrderItems> orderItems;
    private Table table;
    private Attendant attendant;
    private double orderValue;
    private LocalDateTime timeOrdered;

    public Order() {
        this.timeOrdered = LocalDateTime.now();
        this.orderItems = new ArrayList<OrderItems>();

    }

    public Order(Table table, Attendant attendant, LocalDateTime timeOrdered, LocalDateTime timeDelivered) {
        this.orderItems = new ArrayList<OrderItems>();
        this.table = table;
        this.attendant = attendant;
        this.timeOrdered = LocalDateTime.now();
    }

    public Order doneOrderToBilling(Table table, Attendant attendant, LocalDateTime timeOrdered, LocalDateTime timeDelivered) {
        return new Order(table, attendant, timeOrdered, timeDelivered);
    }

    public OrderItems getOrderItemByNumber(int index) {
        if (index - 1 >= 0 && index - 1 < orderItems.size()) {
            return orderItems.get(index - 1);
        } else {
            return null;  // or throw an exception
        }
    }

    public List<OrderItems> getOrderItems() {
        return orderItems;
    }

    public void addToOrder(OrderItems orderItems) {
        this.orderItems.add(orderItems);
        this.orderItems.sort(Comparator.comparing(o -> o.getItem().getName()));

    }

    public void deleteFromOrder(OrderItems orderItems) {
        this.orderItems.remove(orderItems);
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Attendant getAttendant() {
        return attendant;
    }

    public void setAttendant(Attendant attendant) {
        this.attendant = attendant;
    }

    public double getValorTotalPedido() {
        return orderValue;
    }

    public void setValorTotalPedido(double valorPedido) {
        this.orderValue = this.orderItems.stream().mapToDouble(OrderItems::getItemTotalValue).sum();
    }

    public LocalDateTime getTimeOrdered() {
        return timeOrdered;
    }

    public void setTimeOrdered(LocalDateTime timeOrdered) {
        this.timeOrdered = timeOrdered;
    }

    public String toString() {

        if (!orderItems.isEmpty()) {
            int counter = 1;
            StringBuilder string = new StringBuilder("--Lista de itens do pedido:\n");
            for (OrderItems orderItems : orderItems) {
                string.append("---Item n°" + counter + "\n");
                string.append(orderItems.toString()).append(",\n ");
                counter++;
            }
            return string.toString();
        } else {
            return "Lista de Pedidos vazia.";
        }
    }

    public static Order newOrderToBilling(PersonDataRegistry localRegistry, Menu menu, Attendance attendance, Scanner scanner) {
        int idSelection;
        int quantitySelection;
        int verificatorChoice;
        boolean verificator = true;
        OrderItems newOrderItem;
        Order newOrder = new Order();
        try {
            System.out.println("POR FAVOR SELECIONE UM ATENDENTE DA MESA PARA O PEDIDO");
            System.out.println(attendance.getTable().getTableInfo());
            idSelection = scanner.nextInt();
            scanner.nextLine();
            newOrder.setTable(attendance.getTable());
            newOrder.setAttendant((Attendant) localRegistry.findEmployeeById(idSelection));
            do {
                System.out.println(menu);
                System.out.println("POR FAVOR, SELECIONE O PROXIMO ITEM DO PEDIDO \nINSIRA O ID DO ITEM NO CARDAPIO");
                idSelection = scanner.nextInt();
                scanner.nextLine();
                System.out.println("POR FAVOR, INSIRA A QUANTIDADE DO  ITEM SELECIONADO, NO PEDIDO");
                quantitySelection = scanner.nextInt();
                scanner.nextLine();
                newOrderItem = new OrderItems(Menu.findItemById(idSelection, menu), quantitySelection);
                newOrder.addToOrder(newOrderItem);

                System.out.println("GOSTARIA DE ADICIONAR MAIS ITEMS AO PEDIDO?\n SELECIONE             1- SIM              2- NÃO");
                verificatorChoice = scanner.nextInt();
                scanner.nextLine();
                switch (verificatorChoice) {
                    case 1:
                        break;
                    case 2:
                        return newOrder;

                    default:
                        throw new IllegalStateException("Unexpected value: " + verificatorChoice);
                }
            } while (verificator);
        } catch (InputMismatchException e) {
            System.out.println("Entrada Invalida. Por favor insira um numero.");
        } catch (NullPointerException e) {
            System.out.println("Ocorreu um erro. Por favor verifique.");
            e.getMessage();
        }
    return null;
    }
}
