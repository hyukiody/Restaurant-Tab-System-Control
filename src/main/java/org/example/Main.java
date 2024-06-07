package org.example;

import entities.Client;
import platforms.Attendance;
import platforms.Region;
import services.DeliveryServices;
import services.Menu;
import sets.BillingHistory;
import sets.PersonDataRegistry;

import java.util.*;

import static platforms.Attendance.menuAttendances;
import static services.DeliveryServices.deliveryMenu;

public class Main {

    private static void menuAdm(Region localRegion, PersonDataRegistry localRegistry, BillingHistory pastBillings, Scanner scanner) {

        try {
            int admChoice;

            do {

                System.out.println("\n\n            1 - Cadastrar Funcionarios          2 - Cadastrar Clientes              3 -Exibir funcionarios cadastrados                4 - Relatorios anteriores \n              5-Realizar fechamento diario                        6- Exibir clientes cadastrados    \n 7 - Voltar");

                admChoice = scanner.nextInt();
                scanner.nextLine();


                switch (admChoice) {
                    case 1:
                        localRegistry.registerNewEmployee(localRegistry, scanner);
                        viewEmployeesInLocalRegistry(localRegistry);

                        break;
                    case 2:
                        Client client = new Client().registerNewClient(localRegistry, scanner);
                        break;
                    case 3:
                        viewEmployeesInLocalRegistry(localRegistry);
                        break;
                    case 4:
                        viewPastDailyReports();
                        //read and display history of attendances saved at pc files
                        break;
                    case 5:
                        // closeDayAttendances(localRegion.getAttendances()){};//PENDING
                        // generating daily reports and saving to local file for further loading
                        break;
                    case 6:
                        System.out.println(localRegistry.viewClientsInRegistry());
                        break;
                    case 7:
                        break;
                    default:
                        System.out.println("Escolha invalida. Por favor insira opção válida");
                }
            } while (admChoice != 7);
        } catch (InputMismatchException e) {
            System.out.println("Entrada invalida. Por favor tente novamente");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Um erro ocorreu 1: " + e.getMessage());
            e.printStackTrace();
        }
    }

    //to be implemented  method to show past billings; basically a sequence of system.out.println filtered by date;
    private static void viewPastDailyReports() {
    }

    //to be implemented method supposed to transfer the daily billings to an uneditable billing record
    private static void closeDayAttendances(Attendance attendances) {
    }

    //static method to search the employees list of the PersonDataRegistry type variable given;
    private static void viewEmployeesInLocalRegistry(PersonDataRegistry localRegistry) {
        System.out.println(localRegistry.viewEmployeesInRegistry());
    }


    //
    private static void menuPrincipal(Region localRegion, PersonDataRegistry localRegistry, Menu localMenu, BillingHistory pastBillings, DeliveryServices deliveries, Scanner scanner) {

        try {
            int menuChoice;
            do {
                do {
                    System.out.println("--Menu Principal--\n1 - Atendimentos            2 - Cardapio            3 - RESERVAS            4 - Cadastros e Registros\n            5 - Sair");

                    if (scanner.hasNextInt()) {
                        menuChoice = scanner.nextInt();
                        scanner.nextLine();
                    } else {
                        scanner.nextLine();
                        menuChoice = scanner.nextInt();
                        scanner.nextLine();
                    }
                } while (menuChoice < 1 || menuChoice > 5); // validate choice range


                switch (menuChoice) {
                    case 1:
                        System.out.println("Starting menuAttendances...");
                        menuAttendances(localRegion, localRegistry, localMenu, pastBillings, scanner);
                        System.out.println("Finished menuAttendances");
                        break;
                    case 2:
                        System.out.println("Starting menuMenu...");
                        localMenu.menuMenu(localMenu, scanner);
                        System.out.println("Finished menuMenu");
                        break;
                    case 3:
                        System.out.println("Starting menuDeliveries...");
                        deliveryMenu(localRegistry, deliveries.getReservationOrders(), pastBillings, localMenu, scanner);
                        System.out.println("Finished menuDeliveries");
                        break;
                    case 4:
                        System.out.println("Starting menuAdm...");
                        menuAdm(localRegion, localRegistry, pastBillings, scanner);
                        System.out.println("Finished menuAdm");
                        break;
                    case 5:
                        System.out.println("Exiting program.");
                        break;

                }
            } while (menuChoice != 5);
        } catch (InputMismatchException e) {
            System.out.println("Entrada invalida. Tente novamente");
            throw new InputMismatchException();
        } catch (Exception e) {
            System.out.println("Um erro ocorreu: 0 " + e.getMessage());
            e.printStackTrace();
        }

    }


    private static void menuDeliveries(Scanner scanner) {
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        Region localRegion = new Region();
        PersonDataRegistry localRegistry = new PersonDataRegistry();
        Menu localMenu = new Menu();
        BillingHistory billingHistory = new BillingHistory();
        DeliveryServices deliveries = new DeliveryServices();

        Scanner scanner = new Scanner(System.in);
        try {
            menuPrincipal(localRegion, localRegistry, localMenu, billingHistory, deliveries, scanner);
        } catch (Exception e) {
            System.out.println("Ocorreu um erro 11" + e.getMessage());
            e.printStackTrace();
        }
    }

}
