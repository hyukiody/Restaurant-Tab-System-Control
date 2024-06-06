package entities;

import sets.Attendant;

import java.util.List;

public class Clerk extends entities.Employee implements Attendant {
    public Clerk(String nome, String telefone, int idade, String sexo, String email, String cpf, Address address,  List<Employee> employees) {
        super(nome, telefone, idade, sexo, email, cpf, address, employees);
    }
    @Override
    public String getName(){
        return super.getName();
    }
    @Override
    public Attendant getAttendant() {
        return null;
    }

    @Override
    public int getId() {
        return this.getIdAuth();
    }
}
