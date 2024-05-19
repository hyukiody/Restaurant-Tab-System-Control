package platforms;

import entities.Client;
import entities.Employee;
import services.Menu;
import services.accountance.Billing;
import services.accountance.Order;
import sets.Attendant;
import sets.PersonDataRegistry;

import java.io.IOException;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Attendance {

    private Table table;
    private Billing billing;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Attendance(Table table, Client client, List<Billing> pastBillings) {
        this.table = table;
        this.billing = new Billing(client, pastBillings);
        this.startTime = LocalDateTime.now();
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public Billing getBilling() {
        return billing;
    }

    public void setBilling(Billing billing) {
        this.billing = billing;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public void endAttendance() {
        if ("Paid".equals(this.billing.getPaymentStatus())) {

        } else {
        }
    }

    @Override
    public String toString() {
        return "Atendimento Iniciado em: " + this.startTime.toString() + "\n Mesa:" + this.table.getTableInfo() + "\n " + this.billing.toString();
    }


    public static void menuAttendances(Region region, PersonDataRegistry localRegistry, Menu menu, List<Billing> pastBillings, Scanner scanner) {
        System.out.println("\n\n1 - Iniciar novo atendimento        2 - Continuar atendimento       3 - Exibir atendimentos em andamento\n\n4 - Encerrar atendimento pagamento      5 - Exibir atendimentos encerrados hoje         6- Voltar");
        int choice = scanner.nextInt();
        scanner.nextLine();
        while (choice != 6) {
            if (choice == 1) {
                System.out.println("\n\n\\n1- Cadastrar novo cliente        2 - Iniciar sem cadastro         3 - Ja possui cadastro\n");
                int choice12 = scanner.nextInt();
                scanner.nextLine();
                if (choice12 == 1) {
                    Client client = new Client().registerNewClient(localRegistry.getClientsList(), scanner);
                    Attendance attendance = newAttenNewClient(region, client, pastBillings, scanner);
                    region.addAttendance(attendance);
                    continueAttendance(localRegistry, attendance, menu, scanner);
                }
                if (choice12 == 2) {
                    Attendance attendance = newAttenNewClient(region, new Client().newUnnamedClient(), pastBillings, scanner);
                    region.addAttendance(attendance);
                    continueAttendance(localRegistry, attendance, menu, scanner);
                }
                if (choice12 == 3) {
                }
                if (choice12 == 4) {
                    //to close an attendance --> billingStatus=paid
                }
                if (choice12 == 5) {
                    //to show all billings closed on the current day;
                }
                if (choice12 == 6) {
                    return;
                }

            }
            if (choice == 2) {
                System.out.println("Por favor insira o numero da mesa que deseja continuar o atendimento: ");
                region.viewOccupiedTables();
                int tableNumber = scanner.nextInt();
                scanner.nextLine();
                Table table = region.getTableByNumber(tableNumber);
                continueAttendance(localRegistry, region.getAttendanceByTable(table), menu, scanner);
            }
            if (choice == 3) {
                region.viewOpenAttendances();
            } else if (choice == 4) {
            } else if (choice == 5) {
            }
        }

    }

    private static void continueAttendance(PersonDataRegistry localRegistry, Attendance attendance, Menu menu, Scanner scanner) {
        try {
            //checking if there are attendants added to the attendance table
            if (attendance.getTable().getAttendants().isEmpty()) {
                if (localRegistry.getEmployeesList().isEmpty()) {
                    System.out.println("Não há funcionarios registrados; por favor cadastre um novo funcionario agora:");

                }
                addWaiterToAttendance(localRegistry,attendance,scanner);
            }
            int choice;
            do {
                System.out.println(attendance.toString());
                System.out.println("Selecione uma opção  válida: \n" +
                        "1 - CRIAR NOVO PEDIDO               2 - REMOVER PEDIDO DA NOTA              3 - ADICIONAR GARÇOM AO ATENDIMENTO\n                    " +
                        "4 - REMOVER GARÇOM DO ATENDIMENTO                   5 - ENCERRAR ATENDIMENTO\n              " +
                        "OU INSIRA 0 PARA VOLTAR");
                choice = scanner.nextInt();
                scanner.nextLine();
            } while (choice < 0 || choice > 5);
            if (choice == 0) {
                return;
            }
            if (choice == 1) {
                System.out.println(attendance.getBilling().toString());
                attendance.getBilling().addOrder(Order.newOrderToBilling(localRegistry, menu, attendance, scanner));
                attendance.getBilling().updateBillAttendantsFromOrders();
            }
            if (choice == 2) {
                System.out.println(attendance.getBilling().toString());
                attendance.getBilling().removeOrderFromBilling(scanner);
            }
            if (choice == 3) {
                addWaiterToAttendance(localRegistry,attendance,scanner);
            }
            if (choice == 4) {
                removeWaiterrFromAttendance(localRegistry,attendance,scanner);
            }
            if (choice == 5) {
                System.out.println("GOSTARIA DE ENCERRAR ESTE ATENDIMENTO?" +
                        "\n             UMA VEZ ENCERRADO, O ATENDIMENTO NÃO PODERÁ MAIS SER ALTERADO.\n" +
                        "                   1 - SIM                 2- NÃO");
                int choice51=scanner.nextInt();
                scanner.nextLine();
                if(choice51==1){
                    attendance.billing.makePayment();
                    if(attendance.billing.getPaymentStatus().equals("Paid")){

                    }
                }
                if(choice51==2){
                    return;
                }
            }
        } catch (InputMismatchException e) {
            e.getMessage();
        } catch (Exception e) {
            e.getMessage();
        }

    }

    private static Attendance newAttenNewClient(Region region, Client client, List<Billing> pastBillings, Scanner scanner) {
        region.viewAvailableTables();
        System.out.println("Escolha a mesa disponivel desejada:  \n");

        try {
            int tableNumber;
            do {
                tableNumber = scanner.nextInt();
                scanner.nextLine();
            } while (region.getTableByNumber(tableNumber) == null);

            Table table = region.getTableByNumber(tableNumber);
            table.setClient(client);
            /*if (table == null || table.getClient() != null) {
                throw new IllegalArgumentException("Mesa não disponível.");
            }*/
            return new Attendance(table, client, pastBillings);
        } catch (InputMismatchException e) {
            throw new IllegalArgumentException("Entrada inválida. Por favor insira um numero para a mesa.");
        }
    }

    private static void addWaiterToAttendance(PersonDataRegistry localRegistry, Attendance attendance, Scanner scanner) {
        try {
            System.out.println("Por favor adicione um garçom ao atendimento");
            System.out.println(localRegistry.viewEmployeesInRegistry(localRegistry.getEmployeesList()));
            System.out.println("Insira o id do funcionario a adicionar ao atendimento OU INSIRA 0 PARA CANCELAR: ");
            int id;
            boolean verifier = true;
            do {
                id = scanner.nextInt();
                scanner.nextLine();
                verifier = false;
                for (Employee employee : localRegistry.getEmployeesList()) {
                    if (employee.getIdAuth() == id) {
                        verifier = true;
                    }
                }
            } while (verifier);
            if (id == 0) return;
            Attendant attendant = (Attendant) localRegistry.findEmployeeById(id);
            if (attendant != null) {
                attendance.getTable().addAttendants(attendant);
            } else {
                System.out.println("No attendant found with the provided id.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
        } catch (NullPointerException e) {
            System.out.println("An error occurred. Please make sure all inputs are valid.");
        }
    }
    private static void removeWaiterrFromAttendance(PersonDataRegistry localRegistry, Attendance attendance, Scanner scanner){
        System.out.println("POR FAVOR ESCOLHA O ATENDENTE A REMOVER");
        System.out.println(localRegistry.viewEmployeesInRegistry(localRegistry.getEmployeesList()));
        System.out.println("INSIRA O ID DO FUNCIONARIO A REMOVER DO ATENDIMENTO OU INSIRA 0 PARA CANCELAR");
        int id=scanner.nextInt();
        scanner.nextLine();
        for(Attendant attendant : attendance.getBilling().getAttendantList()){
            if( attendant.getId()==id){
                attendance.getBilling().getAttendantList().remove((attendant));

            }
        }
    }
}
