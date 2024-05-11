package entities;

import services.accountance.Billing;
import sets.Address;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Client extends Person {
    private List<Billing> billHistory;
    public Client(){
        super();
        this.billHistory = new ArrayList<Billing>();
        }
    public Client(String name,
                  String phone,
                  int age,
                  String gender,
                  String email,
                  String cpf,
                  Address address) {
        super(name, phone, age, gender, email, cpf, address);
        this.billHistory = new ArrayList<Billing>();
    }
    public Client newUnnamedClient(){
        return new Client("unknown", "unknown", 0, "unknown", "unknown" ,"unknown", new Address().unknownAddress());
    }

    public Client registerNewClient() {
        Client client = null;
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Insira abaixo os dados do cliente a registrar");
            System.out.println("Insira o nome do cliente a registrar:  ");
            String name = sc.nextLine();
            System.out.println("Insira o telefone:  ");
            String phone = sc.nextLine();
            System.out.println("Insira abaixo a idade do cliente:  ");
            int age = sc.nextInt();
            sc.nextLine();
            System.out.println("Insira abaixo o sexo do cliente: F/ M / N (Não Binario)");
            String gender = sc.nextLine();
            System.out.println("Insira abaixo o email do cliente:  ");
            String email = sc.nextLine();
            System.out.println("Insira abaixo o cpf do cliente:  ");
            String cpf = sc.nextLine();
            System.out.println("Digite a rua e numero do novo endereço:");
            String newStreetAndNumber = sc.nextLine();
            System.out.println("Digite o bairro do novo endereço:");
            String newBlock = sc.nextLine();
            System.out.println("Digite o CEP do novo endereço:");
            String newCep = sc.nextLine();
            System.out.println("Digite a cidade do novo endereço:");
            String newCity = sc.nextLine();
            Address address = new Address(newStreetAndNumber, newBlock, newCep, newCity);
            sc.close();
            client = new Client(name, phone, age, gender, email, cpf, address);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter the correct data type.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        return client;
    }
    @Override
    public String toString(){
        return "Cliente: \nNome: " + getName() + "Telefone: " + getPhone() + "Idade: " + getAge()+ "\nGenero :"+ getGender() +"E-mail:" + getEmail() +"CPF: " + getCpf() + "\nEndereço: "+ getAddress().toString();
    }


    public void retrieveClient(){}
}
