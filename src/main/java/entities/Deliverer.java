package entities;

import accountance.Adress;

public class Deliverer extends Employee implements Attendant {
    public Deliverer(String name, String phone, int age, String gender, String email, String cpf, Adress adress, int idAuth) {
        super(name, phone, age, gender, email, cpf, adress, idAuth);
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
