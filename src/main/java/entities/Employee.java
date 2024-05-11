package entities;

import sets.Address;
import sets.Attendant;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public abstract class Employee extends Person {
    private int idAuth;

    public Employee(List<Employee> employees) {
        super();
        this.idAuth = generateIdAuth(employees);
        employees.add(this);
    }

    public Employee(String name, String phone, int age, String gender, String email, String cpf, Address address, List<Employee> employees) {
        super(name, phone, age, gender, email, cpf, address);
        this.idAuth = generateIdAuth(employees);
        employees.add(this);
    }

    public int getIdAuth() {
        return idAuth;
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
        } while (exists);
        return id;
    }

    public String getClassName() {
        return this.getClass().getSimpleName();
    }



    @Override
    public String toString() {
        return "Dados do funcionario:\nName: " + getName() + "\nTelefone: " + getPhone() + "\nAge: " + getAge() + "\nGender: " + getGender() + "\nEmail: " + getEmail() + "\nCPF: " + getCpf() + "\nAddress: " + getAddress().toString() + "\nID: " + getIdAuth();
    }
}
