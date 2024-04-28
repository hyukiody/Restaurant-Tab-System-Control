package entities;

import services.accountance.Billing;
import sets.Address;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
                  String email,
                  String cpf,
                  Address address) {
    super(name, phone, age, gender, email, cpf,address);
    this.billHistory = new ArrayList<>();

    }
    public Client registerNewClient() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Insira abaixo os dados do cliente a registrar");
        System.out.println("Insira o nome do cliente a registrar:  ");
        String name = sc.nextLine();
        System.out.println("Insira o telefone:  ");
        String phone = sc.nextLine();
        System.out.println("Insira abaixo a idade do cliente:  ");
        int age = sc.nextInt();
        System.out.println("Insira abaixo o sexo do cliente: F/ M / N (Não Binario)");
        String gender = sc.nextLine();
        System.out.println("Insira abaixo o email do cliente:  ");
        String email = sc.nextLine();
        System.out.println("Insira abaixo o cpf do cliente:  ");
        String cpf = sc.nextLine();
        System.out.println("Digite a rua do novo endereço:");
        String newStreetAndNumber = sc.nextLine();
        System.out.println("Digite o bairro do novo endereço:");
        String newBlock = sc.nextLine();
        System.out.println("Digite o CEP do novo endereço:");
        String newCep = sc.nextLine();
        System.out.println("Digite a cidade do novo endereço:");
        String newCity = sc.nextLine();
        Address address = new Address(newStreetAndNumber, newBlock, newCep, newCity);
        sc.close();
        return new Client(name, phone,age, gender, email, cpf, address);
    }



    public void retrieveClient(){}
}
