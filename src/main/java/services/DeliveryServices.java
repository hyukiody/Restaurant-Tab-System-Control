package services;

import entities.Address;
import entities.Client;
import entities.Person;
import org.jetbrains.annotations.NotNull;
import platforms.Attendance;
import services.accountance.Billing;
import services.accountance.OrderItems;
import services.accountance.ReservationOrder;
import sets.Attendant;
import sets.BillingHistory;
import sets.PersonDataRegistry;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class DeliveryServices {
    private List<ReservationOrder> reservationOrders;
    private String filename = "ReservationAgenda.txt";

    public DeliveryServices() {
        this.reservationOrders = new ArrayList<ReservationOrder>();

    }

    public static void deliveryMenu(PersonDataRegistry localRegistry, List<ReservationOrder> reservationOrders, BillingHistory pastBillings, Menu menu, Scanner scanner) {
        int menuChoice;
        do {
            System.out.println("1 - NOVA RESERVA                    2-CONFIRMAR ENTREGA                3- VISUALIZAR RESERVAS            4- VISUALIZAR NOTAS FICAIS DE RESERVAS CONFIRMADAS (ENTREGUES)                 5-REMOVER RESERVA                 0- VOLTAR AO MENU PRINCIPAL");
            menuChoice = scanner.nextInt();
            scanner.nextLine();
            switch (menuChoice) {
                case 1:
                    ReservationOrder newReserve = newReservationOrder(localRegistry, menu, scanner);
                    if (newReserve != null) {
                        reservationOrders.add(newReserve);
                    }


                    break;
                case 2:
                    confirmReservation(reservationOrders, pastBillings, scanner);

                    break;
                case 3:
                    viewReservations(reservationOrders);

                    break;
                case 4:
                    viewConfirmedReservations(pastBillings);
                    break;
                case 5:
                    removeReservation(reservationOrders, scanner);
                    break;
                case 0:
                    return;
            }
        } while (menuChoice != 0);
    }

    private static void viewConfirmedReservations(BillingHistory pastBillings) {
        for (Billing pastReservationBill : pastBillings.getPastBillings()) {
            if (pastReservationBill.getReservationOrder() != null) {
                System.out.println(pastReservationBill);
            }
        }
    }

    private static ReservationOrder findReservationOrder(List<ReservationOrder> reservationOrders, Scanner scanner) {
        try {
            System.out.println("POR FAVOR INSIRA O NUMERO DO PEDIDO");
            viewReservations(reservationOrders);
            int index = scanner.nextInt() - 1;
            return reservationOrders.get(index);
        } catch (InputMismatchException e) {
            System.out.println("Entrada invalida; insira um valor inteiro.");
            scanner.next(); // to consume the invalid token
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Indice invalido; insira um valor entre 1 e   " + reservationOrders.size());
        }
        return null; // return null or a default ReservationOrder if an exception is caught
    }

    private static void confirmReservation(List<ReservationOrder> reservationOrders, BillingHistory pastBillings, Scanner scanner) {
        System.out.println("Para confirmar uma reserva, é necessário que o horário agendado de entrega esteja a pelo menos 1 hora a partir de agora\nSELECIONE UMA RESERVA A CONFIRMAR");
        ReservationOrder selectedReservation = findReservationOrder(reservationOrders, scanner);
        if (ChronoUnit.MINUTES.between(LocalDateTime.now(), selectedReservation.getTimeScheduled()) <= 60) {
            pastBillings.getPastBillings().add(new Billing(pastBillings.getPastBillings(), selectedReservation));
            reservationOrders.remove(selectedReservation);
        } else {
            System.out.println("TEMPO DE TOLERÂNCIA NÃO ATINGIDO");
        }
    }

    private static void viewReservations(List<ReservationOrder> reservationOrders) {
        int counter = 1;
        if (!reservationOrders.isEmpty()) {
            StringBuilder body = new StringBuilder("RESERVAS REGISTRADAS| POR ORDEM DE PEDIDO: \n");
            for (ReservationOrder reservationOrder : reservationOrders) {
                body.append("Reserva n° ").append(counter).append(reservationOrder.toString());
                counter++;
            }
            System.out.println(body);
        } else {
            System.out.println("NÃO HÁ RESERVA REGISTRADA");
        }
    }

    public static ReservationOrder newReservationOrder(PersonDataRegistry localRegistry, Menu menu, Scanner scanner) {
        int idSelection;
        int quantitySelection;
        int verificatorChoice;
        int reservationChoice;
        boolean verificator = true;
        OrderItems newOrderItem;
        ReservationOrder newReservationOrder = new ReservationOrder(null, null, null, null, null);
        try {
            Attendant selectedAttendant = null;
            do {
                System.out.println("POR FAVOR SELECIONE O TIPO DA RESERVA:\n                    1- ENTREGA NO ENDEREÇO                  2- RETIRADA  NO BALCÃO              OU INSIRA 0 PARA CANCELAR");
                reservationChoice = scanner.nextInt();
                scanner.nextLine();

                switch (reservationChoice) {
                    case 1:
                        System.out.println(localRegistry.viewEmployeesByType("Deliverer"));
                        System.out.println("INSIRA O ID DO ENTREGADOR DESEJADO(A)");
                        idSelection = scanner.nextInt();
                        scanner.nextLine();
                        selectedAttendant = (Attendant) localRegistry.getEmployeeById(idSelection);
                        break;

                    case 2:
                        System.out.println(localRegistry.viewEmployeesByType("Clerk"));
                        System.out.println("INSIRA O ID DO BALCONISTA DESEJADO(A)");
                        idSelection = scanner.nextInt();
                        scanner.nextLine();
                        selectedAttendant = (Attendant) localRegistry.getEmployeeById(idSelection);

                        break;

                    case 0:
                        return null;
                }

                if (selectedAttendant == null) {
                    System.out.println("ID DE ATENDENTE NÃO ENCONTRADO. POR FAVOR TENTE NOVAMENTE.");
                }
            } while (selectedAttendant == null);
            newReservationOrder.setAttendant(selectedAttendant);
            do {
                System.out.println("POR FAVOR INSIRA A DATA DE ENTREGA OU RETIRADA NO FORMATO dd/MM/yyyy HH:mm");
                String scheduleEntry = scanner.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                try {
                    LocalDateTime entrySchedule = LocalDateTime.parse(scheduleEntry, formatter);
                    newReservationOrder.setTimeScheduled(entrySchedule);
                } catch (DateTimeParseException e) {
                    System.out.println("Data inserida não está no formato correto. Por favor, tente novamente.");
                }
            } while (newReservationOrder.getTimeScheduled() == null);

            do {
                Client clientNewReservation;
                System.out.println("INSIRA O CPF DO CLIENTE A EFETUAR A RESERVA, OU INSIRA 1 PARA ADICIONAR NOVO CLIENTE:\nPOR FAVOR, CPF NO FORMATO \"123.456.789-12\"");
                String consoleEntry = scanner.nextLine();
                if (consoleEntry.equals("1")) {
                    clientNewReservation = Client.registerNewClient(localRegistry, scanner);
                    newReservationOrder.setClient(clientNewReservation);
                } else {
                    if (!consoleEntry.matches("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$")) {
                        System.out.println("CPF incorreto; por favor tente novamente");
                        break;
                    } else {
                        for (Client client : localRegistry.getClientsList()) {
                            if (consoleEntry.equals(client.getCpf())) {
                                newReservationOrder.setClient(client);
                            }
                        }
                    }
                }
            } while (newReservationOrder.getClient() == null);

            do {
                int addressCheck = 0;
                //if the new reservation is a takeaway
                if (reservationChoice == 1) {
                    System.out.println("Para a entrega defina o endereço: Insira 1 para definir um novo endereço de entrega ou insira 2 para utilizar o endereço cadastrado do cliente: " + newReservationOrder.getClient().getAddress());
                    addressCheck = scanner.nextInt();
                    scanner.nextLine();
                    switch (addressCheck) {
                        case 1:
                            Address newOverwritingAddress = Address.newAddress(scanner);
                            newReservationOrder.setReservationAddress(newOverwritingAddress);
                            break;
                        case 2:
                            newReservationOrder.setReservationAddress(newReservationOrder.getClient().getAddress());
                            break;
                    }

                }
                //if the new reservation is a delivery
                else if (reservationChoice == 2) {
                    newReservationOrder.setReservationAddress(new Address("Retirar no balcão do estabelecimento", "00", "Aqui", "0000000"));

                }
            } while (newReservationOrder.getReservationAddress() == null);
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
                        newReservationOrder.addToReservationOrder(newOrderItem);
                        newReservationOrder.refreshTotalOrderValue();
                    }
                } while (newOrderItem.getItem() == null);

                System.out.println("GOSTARIA DE ADICIONAR MAIS ITENS AO PEDIDO?\n SELECIONE             1- SIM              2- NÃO");
                verificatorChoice = scanner.nextInt();
                scanner.nextLine();
                switch (verificatorChoice) {
                    case 1:
                        break;
                    case 2:
                        verificator = false;
                        break;

                    default:
                        throw new IllegalStateException("VALOR INSERIDO INVALIDO, TENTE NOVAMENTE: " + verificatorChoice);
                }
            } while (verificator);

        } catch (InputMismatchException e) {
            System.out.println("Entrada Invalida. POR FAVOR TENTE NOVAMENTE.");
            scanner.nextLine(); // consume the invalid token
        } catch (NullPointerException e) {
            System.out.println("Ocorreu um erro. Por favor verifique.");
            e.printStackTrace();
        }

        return newReservationOrder;
    }


    private static void removeReservation(List<ReservationOrder> reservationOrders, Scanner scanner) {
        viewReservations(reservationOrders);
        System.out.println("Por favor insira o número da reserva a ser removida: ");
        try {
            int reservationIndex = scanner.nextInt();
            scanner.nextLine();
            reservationOrders.remove(reservationOrders.get(reservationIndex));
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
            scanner.next(); // to consume the invalid token
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Índice inválido. Por favor, insira um número entre 0 e " + (reservationOrders.size() - 1));
        }
    }

    public List<ReservationOrder> getReservationOrders() {
        return this.reservationOrders;
    }
}
