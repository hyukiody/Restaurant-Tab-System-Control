package entities;

import sets.Attendant;
import sets.PersonDataRegistry;

import java.util.List;

public class Waiter extends entities.Employee implements sets.Attendant {

    public Waiter(String name, String phone, int age, String gender, String email, String cpf, Address address, PersonDataRegistry localRegistry) {
        super(name, phone, age, gender, email, cpf, address, localRegistry);

    }

    @Override
    public String getName(){
        return super.getName();
    }
    @Override
    public Attendant getAttendant() {
        return this;
    }

    @Override
    public int getId() {
        return this.getIdAuth();
    }

}
