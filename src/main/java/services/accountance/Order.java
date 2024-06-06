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
        if (index - 1 >= 0 && index - 1 < this.orderItems.size()) {
            return this.orderItems.get(index - 1);
        } else {
            return null;  // or throw an exception
        }
    }

    public List<OrderItems> getOrderItems() {
        return this.orderItems;
    }

    public synchronized void addToOrder(OrderItems orderItems) {
    if (orderItems == null || orderItems.getItem() == null) {
        throw new IllegalArgumentException("OrderItems or its item cannot be null");
    }
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
        return this.orderValue;
    }

    public void setValorTotalPedido() {
        this.orderValue = this.orderItems.stream().mapToDouble(OrderItems::getItemTotalValue).sum();
    }

    public LocalDateTime getTimeOrdered() {
        return this.timeOrdered;
    }

    public void setTimeOrdered(LocalDateTime timeOrdered) {
        this.timeOrdered = timeOrdered;
    }

    public String toString() {

        if (!this.orderItems.isEmpty()) {
            int counter = 1;
            StringBuilder string = new StringBuilder("--Lista de itens do pedido:\n");
            for (OrderItems orderItems : this.orderItems) {
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
            Attendant selectedAttendant = null;
            do {
                System.out.println("POR FAVOR SELECIONE UM ATENDENTE DA MESA PARA O PEDIDO              OU INSIRA 0 PARA CANCELAR");
                System.out.println(attendance.getTable().getTableInfo());
                idSelection = scanner.nextInt();
                scanner.nextLine();

                if (idSelection == 0) {
                    return null;
                }

                selectedAttendant = (Attendant) localRegistry.getEmployeeById(idSelection);
                if (selectedAttendant == null) {
                    System.out.println("ID DE ATENDENTE NÃO ENCONTRADO. POR FAVOR TENTE NOVAMENTE.");
                }
            } while (selectedAttendant == null);

            if (selectedAttendant != null) {
                newOrder.setTable(attendance.getTable());
                newOrder.setAttendant(selectedAttendant);
            }
            do {
                do {
                    System.out.println(menu);
                    System.out.println("POR FAVOR, SELECIONE O PROXIMO ITEM DO PEDIDO \nINSIRA O ID DO ITEM NO CARDAPIO                 OU 0 PARA CANCELAR");
                    idSelection = scanner.nextInt();
                    scanner.nextLine();

                    if (idSelection == 0) {
                        return null;
                    }

                    System.out.println("POR FAVOR, INSIRA A QUANTIDADE DO  ITEM SELECIONADO, NO PEDIDO");
                    quantitySelection = scanner.nextInt();
                    scanner.nextLine();

                    newOrderItem = new OrderItems(menu.getItemById(idSelection), quantitySelection);
                    if (newOrderItem.getItem() == null) {
                        System.out.println("ID DE ITEM NÃO ENCONTRADO. POR FAVOR TENTE NOVAMENTE.");
                    } else {
                        newOrder.addToOrder(newOrderItem);
                        newOrder.setValorTotalPedido();
                    }
                } while (newOrderItem.getItem() == null);

                System.out.println("GOSTARIA DE ADICIONAR MAIS ITEMS AO PEDIDO?\n SELECIONE             1- SIM              2- NÃO");
                verificatorChoice = scanner.nextInt();
                scanner.nextLine();
                switch (verificatorChoice) {
                    case 1:
                        break;
                    case 2:
                        verificator=false;
                        break;

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
        return newOrder;
    }
}
