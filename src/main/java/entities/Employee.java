package entities;

import accountance.Adress;

public abstract class Employee extends Person {
    private int idAuth;

    public Employee(String name, String phone, int age, String gender, String email, String cpf, Adress adress, int idAuth) {
        super(name,  phone,  age, gender, email,  cpf, adress);
        this.idAuth = idAuth;
    }

    public int getIdAuth() {
        return idAuth;
    }

    public void setIdAuth(int idAuth) {
        this.idAuth = idAuth;
    }

    public abstract String getName(String name);
}
