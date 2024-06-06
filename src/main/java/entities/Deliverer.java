package entities;

import sets.Attendant;

import java.util.List;

public class Deliverer extends entities.Employee implements Attendant {
    public Deliverer(String name, String phone, int age, String gender, String email, String cpf, Address address, List<Employee> employees) {
        super(name, phone, age, gender, email, cpf, address, employees);
    }

    @Override
    public String getName(){
        return super.getName();
    }


    @Override
    public Attendant getAttendant() {
        return this;
    }

    public int getId() {
        return this.getIdAuth();
    }


}
