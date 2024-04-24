package entities;

import sets.Address;

public abstract class Employee extends Person {
    private int idAuth;

    public Employee(String name, String phone, int age, String gender, String email, String cpf, Address address, int idAuth) {
        super(name,  phone,  age, gender, email,  cpf, address);
        this.idAuth = idAuth;
    }

    public int getIdAuth() {
        return idAuth;
    }

    public void setIdAuth(int idAuth) {
        this.idAuth = idAuth;
    }

}
