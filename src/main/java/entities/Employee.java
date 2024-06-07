package entities;

import sets.Attendant;
import sets.PersonDataRegistry;

import java.util.List;

public abstract class Employee extends Person {
    private int idAuth;

        public Employee(String name, String phone, int age, String gender, String email, String cpf, Address address, PersonDataRegistry localRegistry) {
        super(name, phone, age, gender, email, cpf, address);
        this.idAuth = generateIdAuth(localRegistry.getEmployeesList());
        localRegistry.getEmployeesList().add(this);
    }

    public int getIdAuth() {
        return this.idAuth;
    }

    public void setIdAuth(int idAuth) {
        this.idAuth = idAuth;
    }

    private int generateIdAuth(List<Employee> employees) {
        boolean exists;
        int id;
        do {
            exists=false;
            id = (int) (Math.random() * 1000);


            for (Employee employee : employees) {
                if (employee.getIdAuth() == id) {
                    exists = true;
                }
            }
        } while (exists || id==0);
        return id;
    }

    public String getClassName() {
        return this.getClass().getSimpleName();
    }



    @Override
    public String toString() {
        return " Dados do funcionario:"+getClassName()+"\nName: " + getName() + "         Telefone: " + getPhone() + "\nAge: " + getAge() + "         Gender: " + getGender() + "\nEmail: " + getEmail() + "          CPF: " + getCpf() + "\nAddress: " + getAddress().toString() + "             ID: " + getIdAuth();
    }
}
