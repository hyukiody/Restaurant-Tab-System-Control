package platforms;

import entities.Client;
import entities.Employee;
import services.Menu;
import services.accountance.Billing;
import services.accountance.Order;
import sets.BillingHistory;
import sets.Attendant;
import sets.PersonDataRegistry;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Attendance {

    private Table table;
    private Billing billing;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Attendance(List<Attendance> localAttendances, Table table, Client client, List<Billing> pastBillings) {
        this.table = table;
        this.billing = new Billing(client, pastBillings);
        this.startTime = LocalDateTime.now();
        localAttendances.add(this);
    }

    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    public Billing getBilling() {
        return this.billing;
    }

    public void setBilling(Billing billing) {
        this.billing = billing;
    }

    public Table getTable() {
        return this.table;
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedStartTime = this.startTime.format(formatter);
        return "Atendimento Iniciado em: " + formattedStartTime + "\n Mesa:" + this.table.getTableInfo() + "\n " + this.billing.toString();
    }


    public static void menuAttendances(Region localRegion, PersonDataRegistry localRegistry, Menu menu, BillingHistory pastBillings, Scanner scanner) {
        int choice = 0;
        do {
            System.out.println("\n\n1 - Iniciar novo atendimento        2 - Continuar atendimento       3 - Exibir atendimentos em andamento\n\n4 - Encerrar atendimento pagamento      5 - Exibir atendimentos encerrados hoje         6- Voltar");
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // clear the input buffer
                continue;
            }
            switch (choice) {
                case 1:
                    System.out.println("\n\n\\n1- Cadastrar novo cliente        2 - Iniciar sem cadastro         3 - Ja possui cadastro\n");
                    int choice12 = scanner.nextInt();
                    scanner.nextLine();
                    Attendance attendance;
                    Client client;
                    switch (choice12) {
                        case 1:
                            client = new Client().registerNewClient(localRegistry.getClientsList(), scanner);
                            attendance = newAttenGivenClient(localRegion, client, pastBillings, scanner);
                            continueAttendance(localRegion, localRegistry, attendance, menu, scanner, pastBillings);
                            break;
                        case 2:
                            attendance = newAttenGivenClient(localRegion, new Client().newUnnamedClient(), pastBillings, scanner);
                            continueAttendance(localRegion, localRegistry, attendance, menu, scanner, pastBillings);
                            break;
                        case 3:
                            System.out.println(localRegistry.viewClientsInRegistry());
                            System.out.println("INSIRA O CPF DO CLIENTE JÁ CADASTRADO");
                            String cpfInput = scanner.nextLine();
                            cpfInput = cpfInput.replaceAll("\\D+", "");
                            client = localRegistry.findClientByCpf(cpfInput);
                            attendance = newAttenGivenClient(localRegion, client, pastBillings, scanner);
                            continueAttendance(localRegion, localRegistry, attendance, menu, scanner, pastBillings);
                            //create a new attendance with an exhisting client -> localRegistry retrieving registered clients from existing file
                            break;

                        default:
                            throw new IllegalStateException("VALOR INCORRETO" + choice12 + "POR FAVOR TENTE NOVAMENTE");
                    }
                    break;

                case 2:
                    System.out.println("Por favor insira o numero da mesa que deseja continuar o atendimento: ");
                    localRegion.viewOccupiedTables();
                    int tableNumber = scanner.nextInt();
                    scanner.nextLine();
                    Table table = localRegion.getTableByNumber(tableNumber);
                    attendance = localRegion.getAttendanceByTable(table);
                    continueAttendance(localRegion, localRegistry, attendance, menu, scanner, pastBillings);
                    break;

                case 3:
                    localRegion.viewOpenAttendances();
                    break;

                case 4:
                    break;

                case 5:
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Opção inválida. Por favor tente novamente");
                    break;
            }
        } while (choice != 6);

    }

    private static void continueAttendance(Region region, PersonDataRegistry localRegistry, Attendance attendance, Menu menu, Scanner scanner, BillingHistory pastBillings) {
        try {
            //checking if there are attendants added to the attendance table
            if (attendance.getTable().getAttendants().isEmpty()) {
                if (localRegistry.getEmployeesList().isEmpty()) {
                    System.out.println("Não há funcionarios registrados; por favor cadastre um novo funcionario agora:");

                }
                addWaiterToAttendance(localRegistry, attendance, scanner);
            }
            int choice;
            do {
                System.out.println(attendance);
                System.out.println("\nSelecione uma opção  válida: \n" +
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
                Order newOrder = Order.newOrderToBilling(localRegistry, menu, attendance, scanner);
                attendance.getBilling().addOrderToBilling(newOrder);
                attendance.getBilling().updateBillAttendantsFromBillOrders();

            }
            if (choice == 2) {
                System.out.println(attendance.getBilling().toString());
                attendance.getBilling().removeOrderFromBilling(scanner);
            }
            if (choice == 3) {
                addWaiterToAttendance(localRegistry, attendance, scanner);
            }
            if (choice == 4) {
                removeWaiterrFromAttendance(localRegistry, attendance, scanner);
            }
            if (choice == 5) {
                endAttendance(region, attendance, scanner, pastBillings);
            }
        } catch (InputMismatchException e) {
            e.getMessage();
        } catch (Exception e) {
            e.getMessage();
        }

    }

    private void setEndTime(LocalDateTime localDateTime) {
        this.endTime = localDateTime;
    }

    private static Attendance newAttenGivenClient(Region localRegion, Client client, BillingHistory pastBillings, Scanner scanner) {
        localRegion.viewAvailableTables();
        System.out.println("Escolha a mesa disponivel desejada:  \n");

        try {
            int tableNumber;
            do {
                tableNumber = scanner.nextInt();
                scanner.nextLine();
            } while (localRegion.getTableByNumber(tableNumber) == null);

            Table table = localRegion.getTableByNumber(tableNumber);
            table.setClient(client);
            /*if (table == null || table.getClient() != null) {
                throw new IllegalArgumentException("Mesa não disponível.");
            }*/
            return new Attendance(localRegion.getAttendances(), table, client, pastBillings.getPastBillings());
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
            boolean verifier;
            do {
                id = scanner.nextInt();
                scanner.nextLine();
                verifier = false;
                for (Employee employee : localRegistry.getEmployeesList()) {
                    if (employee.getIdAuth() == id) {
                        verifier = true;
                        break;
                    }
                }
                if (!verifier) {
                    System.out.println("ID inválido. Por favor tente novamente.");
                    break;
                }
            } while (!verifier);
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

    private static void removeWaiterrFromAttendance(PersonDataRegistry localRegistry, Attendance attendance, Scanner scanner) {
        System.out.println("POR FAVOR ESCOLHA O ATENDENTE A REMOVER");
        System.out.println(localRegistry.viewEmployeesInRegistry(localRegistry.getEmployeesList()));
        System.out.println("INSIRA O ID DO FUNCIONARIO A REMOVER DO ATENDIMENTO OU INSIRA 0 PARA CANCELAR");
        int id = scanner.nextInt();
        scanner.nextLine();
        for (Attendant attendant : attendance.getBilling().getAttendantList()) {
            if (attendant.getId() == id) {
                attendance.getBilling().getAttendantList().remove((attendant));

            }
        }
    }

    private static void endAttendance(Region localRegion, Attendance attendance, Scanner scanner, BillingHistory pastBillings) {
        try {
            System.out.println("GOSTARIA DE ENCERRAR ESTE ATENDIMENTO?" +
                    "\n             UMA VEZ ENCERRADO, O ATENDIMENTO NÃO PODERÁ MAIS SER ALTERADO.\n" +
                    "                   1 - SIM                 2- NÃO");
            int choice51 = scanner.nextInt();
            scanner.nextLine();
            if (choice51 == 1) {
                if (attendance != null && attendance.billing != null) {
                    attendance.billing.makePayment();
                    if ("Paid".equals(attendance.billing.getPaymentStatus())) {
                        attendance.billing.setPaymentTime(LocalDateTime.now());

                        attendance.setEndTime(LocalDateTime.now());

                        pastBillings.addEndedBilling(attendance.getBilling());
                        attendance.table.cleanTable(attendance.getTable());
                        localRegion.removeAttendance(attendance);
                        attendance = null;

                    }
                } else {
                    System.out.println("Attendance or billing is null.");
                }
            } else if (choice51 == 2) {
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
        } catch (NullPointerException e) {
            System.out.println("An error occurred. Please make sure all inputs are valid.");
        }
    }
}
