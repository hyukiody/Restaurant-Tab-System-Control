package entities;

import sets.Address;

public class Deliverer extends entities.Employee implements sets.Employee {
    public Deliverer(String name, String phone, int age, String gender, String email, String cpf, Address address, int idAuth) {
        super(name, phone, age, gender, email, cpf, address, idAuth);
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


}
