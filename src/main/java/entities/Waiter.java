package entities;

import accountance.Adress;

public class Waiter extends Employee implements Attendant {
    public Waiter(String nome, String telefone, int idade, String sexo, String email, String cpf, Adress adress, int idAutorizacao) {
        super(nome, telefone, idade, sexo, email, cpf, adress, idAutorizacao);
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
