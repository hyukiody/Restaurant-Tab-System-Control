package entities;

import sets.Address;
import sets.Attendant;

public class Waiter extends Employee implements Attendant {
    public Waiter(String nome, String telefone, int idade, String sexo, String email, String cpf, Address address, int idAutorizacao) {
        super(nome, telefone, idade, sexo, email, cpf, address, idAutorizacao);
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
