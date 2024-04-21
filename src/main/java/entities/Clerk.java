package entities;

import accountance.Adress;

public class Clerk extends Employee implements Attendant {
    public Clerk(String nome, String telefone, int idade, String sexo, String email, String cpf, Adress adress, int idAutorizacao) {
        super(nome, telefone, idade, sexo, email, cpf, adress, idAutorizacao);
    }

        @Override
    public void performOrder() {
        System.out.println("Request realizado");
    }

    @Override
    public void scheduleOrder() {
        System.out.println("Request agendado");
    }

    @Override
    public void deliverOrder() {
        System.out.println("Request entregue");
    }

    @Override
    public String getName(){
        return null;
    }
}
