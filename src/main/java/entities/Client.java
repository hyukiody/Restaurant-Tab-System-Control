package entities;

import accountance.Billing;
import accountance.Adress;
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
                  Adress address,
                  String email,
                  String cpf,
                  Adress adress) {
    super(name, phone, age, gender, email, cpf,adress);
    this.billHistory = new ArrayList<>();

    }
}
