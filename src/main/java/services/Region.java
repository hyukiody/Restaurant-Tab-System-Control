package services;

import platforms.Table;
import sets.Tables;

import java.util.ArrayList;
import java.util.List;

public class Region {
    private List<Attendance> attendances;
    private List<Table> tables;

    public Region(){
        this.attendances = new ArrayList<>();
        this.tables = new ArrayList<>();
        for(Tables table : Tables.values()){
            this.tables.add(new Table());
        }
    }
    public void viewOpenAttendances(){
        if(!attendances.isEmpty()) {
            System.out.println("Atendimentos em andamento: ");
            for (Attendance attendance : this.attendances) {
                System.out.println(attendance.toString());
            }
        }
        else{
            System.out.println("Não há atendimentos em andamento.");
        }
        //to show all ongoing attendances and their respective tables
    }
    public Attendance  getAttendances(){
        return (Attendance) this.attendances;
    }
    public void addAttendance(List<Attendance> attendances){
        this.attendances.add((Attendance) attendances);
    }
}