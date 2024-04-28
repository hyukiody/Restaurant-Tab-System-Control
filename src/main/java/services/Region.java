package services;

import platforms.Table;
import sets.TablesList;

import java.util.ArrayList;
import java.util.List;

public class Region {
    private List<Attendance> attendances;
    private List<Table> tables;

    public Region() {
        this.attendances = new ArrayList<Attendance>();
        this.tables = new ArrayList<Table>();
        for (TablesList tableList : TablesList.values()) {
            Table table = new Table();
            table.setNumero(tableList.ordinal() + 1); // ordinal() returns the position of the enum constant
            this.tables.add(table);
        }
    }

    public void viewOpenAttendances() {
        if (!attendances.isEmpty()) {
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
        for (Table table : this.tables) {
            if(table.getClient()!=null){
             table.viewTableInfo();
            }

        }
    }

    public Attendance getAttendances() {
        return (Attendance) this.attendances;
    }

    public void addAttendance(Attendance attendance) {

        this.attendances.add(attendance);
    }

    public void viewAvailableTables() {
        for(Table table : this.tables){
            if(table.getClient()==null){
                System.out.println("Mesa " + table.getNumero() + " está disponível.");
        }
    }
}

    public Table getTableByNumber(int tableNumber) {
        for(Table table : this.tables){
            if(table.getNumero() == tableNumber){
                return table;
            }
        }
        return null;
    }
    public Attendance getAttendanceByTable(Table table){
        for(Attendance attendance : this.attendances){
            if(attendance.getTable().equals(table){
                return attendance;
            }
        }
        return null;
    }
    }