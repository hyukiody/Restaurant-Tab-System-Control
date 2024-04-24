package entities;

import services.accountance.Billing;
import sets.Address;

import java.util.ArrayList;
import java.util.List;

public class Client extends Person {
    private List<Billing> billHistory;
    public Client(){
        super();
        this.billHistory = new ArrayList<>();
        }
    public Client(String name,
                  String phone,
                  int age,
                  String gender,
                  Address address,
                  String email,
                  String cpf,
                  Address adress) {
    super(name, phone, age, gender, email, cpf,adress);
    this.billHistory = new ArrayList<>();

    }
    public void cadastrarCliente(){}

    public void removerCliente(){}
}
