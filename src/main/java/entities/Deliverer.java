package entities;

import sets.Address;
import sets.Attendant;

import java.util.List;

public class Deliverer extends entities.Employee implements Attendant {
    public Deliverer(String name, String phone, int age, String gender, String email, String cpf, Address address, List<Employee> employees) {
        super(name, phone, age, gender, email, cpf, address, employees);
    }



    @Override
    public void performOrder() {

    }

    @Override
    public void scheduleOrder() {

    }

    @Override
    public void deliverOrder() {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Attendant registerNewAttendant() {
        return null;
    }

    @Override
    public Attendant getAttendant() {
        return null;
    }


}
