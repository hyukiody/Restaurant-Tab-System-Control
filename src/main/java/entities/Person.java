package entities;

public abstract class Person {

    private String name;
    private String phone;
    private String cpf;
    private int age;
    private String gender;
    private Address address;
    private String email;

    public Person(String name, String phone, int age, String gender, String email, String cpf, Address address) {
        this.name = name;
        this.phone = phone;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.cpf = cpf;
        this.address = address;
    }

    public Person() {

    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void viewInfo(){
        System.out.println("Nome: " + this.getName() + "\nCPF: " + this.getCpf() );
    }
}
