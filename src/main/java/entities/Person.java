package entities;

import accountance.Adress;

public abstract class Person {

    private String name;
    private String phone;
    private String cpf;
    private int age;
    private String gender;
    private Adress adress;
    private String email;

    public Person(String name, String phone, int age, String gender, String email, String cpf, Adress adress) {
        this.name = name;
        this.phone = phone;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.cpf = cpf;
        this.adress = adress;
    }

    public Person() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Adress getEndereco() {
        return adress;
    }

    public void setEndereco(Adress adress) {
        this.adress = adress;
    }


}
