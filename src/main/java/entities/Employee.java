package entities;

import sets.Address;

public abstract class Employee extends Person {
    private int idAuth;

    public Employee(String name, String phone, int age, String gender, String email, String cpf, Address address, int idAuth) {
        super(name,  phone,  age, gender, email,  cpf, address);
        this.idAuth = generateIdAuth();
    }

    public int getIdAuth() {
        return idAuth;
    }

    public void setIdAuth(int idAuth) {
        this.idAuth = idAuth;
    }
    private int generateIdAuth() {
        int id;
        do {
            id = (int) (Math.random()*100);
        } while (EmployeeList.checkIdExists(id));
        return id;
    }
    @Override
    public String toString(){
        return "Name: " + getName() + "\nPhone: " + getPhone() + "\nAge: " + getAge() + "\nGender: " + getGender() + "\nEmail: " + getEmail() + "\nCPF: " + getCpf() + "\nAddress: " + getAddress() + "\nID: " + getIdAuth();
    }

}
