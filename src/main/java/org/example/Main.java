package org.example;

import platforms.Attendance;
import platforms.Region;
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
                do {

                    System.out.println("\n\n            1 - Cadastrar Funcionarios          2 - Exibir funcionarios cadastrados              3 - Relatorios anteriores               4 - Realizar fechamento diario \n\n 5 - Voltar");
                    if (scanner.hasNextInt()) {
                        admChoice = scanner.nextInt();
                    } else {

                        scanner.nextLine(); // Consume any invalid input
                        System.out.println("Entrada invalida. Digite um número (1, 2,  3, 4 ou 5): ");
                        admChoice = scanner.nextInt();
                        continue;
                    }
                    scanner.nextLine();

                } while (admChoice < 1 || admChoice > 5); // validate choice range

                switch (admChoice) {
                    case 1:
                        localRegistry.registerNewEmployee(localRegistry.getEmployeesList(), scanner);
                        viewEmployeesInLocalRegistry(localRegistry);

                        break;
                    case 2:
                        viewEmployeesInLocalRegistry(localRegistry);
                        break;
                    case 3:
                        viewPastDailyReports();
                        //read and display history of attendances saved at pc files
                        break;
                    case 4:
                        // closeDayAttendances(localRegion.getAttendances()){};//PENDING
                        // generating daily reports and saving to local file for further loading
                        break;
                    case 5:
                        break;
                    default:
                        System.out.println("Escolha invalida. Por favor insira opção válida");
                }
            } while (admChoice != 5);
        } catch (InputMismatchException e) {
            System.out.println("Entrada invalida. Por favor tente novamente");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Um erro ocorreu 1: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void viewPastDailyReports() {
    }

    private static void
    closeDayAttendances(Attendance attendances) {
    }

    private static void viewEmployeesInLocalRegistry(PersonDataRegistry localRegistry) {
        System.out.println(localRegistry.viewEmployeesInRegistry(localRegistry.getEmployeesList()));
    }

    private static void menuPrincipal(Region localRegion, PersonDataRegistry localRegistry, Menu localMenu, BillingHistory pastBillings, Scanner scanner) {

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
                        menuAttendances(localRegion,localRegistry,localMenu, pastBillings ,scanner);
                        System.out.println("Finished menuAttendances");
                        break;
                    case 2:
                        System.out.println("Starting menuMenu...");
                        localMenu.menuMenu(localMenu,scanner);
                        System.out.println("Finished menuMenu");
                        break;
                    case 3:
                        System.out.println("Starting menuDeliveries...");
                        deliveryMenu(scanner);
                        System.out.println("Finished menuDeliveries");
                        break;
                    case 4:
                        System.out.println("Starting menuAdm...");
                        menuAdm(localRegion, localRegistry, pastBillings ,scanner);
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
        Scanner sc = new Scanner(System.in);
        try {
            menuPrincipal(localRegion, localRegistry, localMenu, billingHistory ,sc);
        } catch (Exception e) {
            System.out.println("Ocorreu um erro 11" + e.getMessage());
            e.printStackTrace();
        }
    }

}
