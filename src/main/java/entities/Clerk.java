package entities;

import sets.Attendant;
import sets.PersonDataRegistry;

import java.util.List;

public class Clerk extends entities.Employee implements Attendant {
    public Clerk(String nome, String telefone, int idade, String sexo, String email, String cpf, Address address, PersonDataRegistry localRegistry) {
        super(nome, telefone, idade, sexo, email, cpf, address, localRegistry);
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
