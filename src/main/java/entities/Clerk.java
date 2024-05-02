package entities;

import sets.Address;
import sets.Attendant;

import java.util.List;

public class Clerk extends entities.Employee implements Attendant {
    public Clerk(String nome, String telefone, int idade, String sexo, String email, String cpf, Address address, int idAutorizacao, List<Employee> employees) {
        super(nome, telefone, idade, sexo, email, cpf, address, idAutorizacao, employees);
    }

        @Override
    public void performOrder() {
        System.out.println("Order realizado");
    }

    @Override
    public void scheduleOrder() {
        System.out.println("Order agendado");
    }

    @Override
    public void deliverOrder() {
        System.out.println("Order entregue");
    }

    @Override
    public String getName(){
        return null;
    }
}
