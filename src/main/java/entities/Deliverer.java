package entities;

import accountance.Adress;

public class Deliverer extends Employee implements Attendant {
    public Deliverer(String nome, String telefone, int idade, String sexo, String email, String cpf, Adress adress, int idAutorizacao) {
        super(nome, telefone, idade, sexo, email, cpf, adress, idAutorizacao);
    }

    @Override
    public String getName(String name) {
        return null;
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
    public String getNome() {
        return null;
    }

    @Override
    public String getName() {
        return null;    }
}
