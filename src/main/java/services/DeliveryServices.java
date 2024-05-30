package services;

import java.util.Scanner;

public class DeliveryServices {






    public static void deliveryMenu(Scanner scanner){
        System.out.println("1 - NOVA RESERVA                2- VISUALIZAR RESERVAS              0- VOLTAR AO MENU PRINCIPAL");
        int menuChoice = scanner.nextInt();
        scanner.nextLine();
        switch(menuChoice) {
            case 1:
                newReservation();

                break;
            case 2:
                viewReservations();
                break;
            case 0:
                return;
        }
    }

    private static void viewReservations(Scanner scanner) {
    }

    private static void newReservation(Scanner scanner) {
    }
}
