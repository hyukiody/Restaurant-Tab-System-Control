package platforms;

import services.accountance.Billing;
import sets.TablesList;

import java.util.ArrayList;
import java.util.List;

public class Region {
    private List<Attendance> attendances;
    private List<Table> tables;


    public Region() {
        this.setAttendances(new ArrayList<Attendance>());
        this.setTables(new ArrayList<Table>());
        for (TablesList tableList : TablesList.values()) {
            Table table = new Table();
            table.setNumero(tableList.ordinal() + 1); // ordinal() returns the position of the enum constant
            this.getTables().add(table);
        }
    }

    public void viewOpenAttendances() {
        if (!this.attendances.isEmpty()) {
            System.out.println("Atendimentos em andamento: ");
            for (Attendance attendance : this.attendances) {
                System.out.println(attendance.toString());
            }
        } else {
            System.out.println("Não há atendimentos em andamento.");
        }
        //to show all ongoing attendances and their respective tables
    }

    public void viewOccupiedTables() {
        for (Table table : this.getTables()) {
            if (table.getClient() != null) {
                table.viewTableInfo();
            }

        }
    }

    public void removeAttendance(Attendance attendance) {
    if (attendance == null) {
        System.out.println("Attendance is null, nothing to remove.");
        return;
    }

    boolean isRemoved = this.attendances.remove(attendance);

    if (isRemoved) {
        System.out.println("Attendance removed successfully.");
    } else {
        System.out.println("Attendance not found in the list.");
    }
}

    public Attendance getAttendances() {
        return (Attendance) this.attendances;
    }

    public void addAttendance(Attendance attendance) {

        this.attendances.add(attendance);
    }

    public void endAttendance(Attendance attendance) {
        if ("Paid".equals(attendance.getBilling().getPaymentStatus())) {

        }
    }

    public void viewAvailableTables() {
        for (Table table : this.getTables()) {
            if (table.getClient() == null) {
                System.out.println("Mesa " + table.getNumero() + " está disponível.");
            }
        }
    }

    public Table getTableByNumber(int tableNumber) {
        for (Table table : this.getTables()) {
            if (table.getNumero() == tableNumber) {
                return table;
            }
        }
        return null;
    }

    public Attendance getAttendanceByTable(Table table) {
        for (Attendance attendance : this.attendances) {
            if (attendance.getTable().equals(table)) {
                return attendance;
            }
        }
        return null;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }
}
