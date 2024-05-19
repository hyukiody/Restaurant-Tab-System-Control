package entities;

import services.accountance.Billing;

import java.util.*;

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

    public Client registerNewClient(List<Client> clients,Scanner scanner) {
        Client client = null;
        try {
            System.out.println("Insira abaixo os dados do cliente a registrar");
            System.out.println("Insira o nome do cliente a registrar:  ");
            String name = scanner.nextLine();
            System.out.println("Insira o telefone:  ");
            String phone = scanner.nextLine();
            System.out.println("Insira abaixo a idade do cliente:  ");
            int age = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Insira abaixo o sexo do cliente: F/ M / N (Não Binario)");
            String gender = scanner.nextLine();
            System.out.println("Insira abaixo o email do cliente:  ");
            String email = scanner.nextLine();
            boolean cpfCheck;
            String cpf;
            do {
                System.out.println("Insira abaixo o cpf do cliente:  ");
                cpf = scanner.nextLine();
                cpfCheck = false;
                for (Client clientCheck : clients) {
                    if (clientCheck.getCpf() == cpf) {
                        System.out.println("CPF de Cliente já encontrado; por favor verifique e tente novamente.");
                        cpfCheck = true;
                    }
                }
            } while (cpfCheck);

            Address address = new Address().newAddress(scanner);
            client = new Client(name, phone, age, gender, email, cpf, address);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter the correct data type.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }if(client!=null){
            clients.add(client);
            Collections.sort(clients, Comparator.comparing(Client::getName));
        }
        return client;
    }
    @Override
    public String toString(){
        return "Cliente: \nNome: " + getName() + "Telefone: " + getPhone() + "Idade: " + getAge()+ "\nGenero :"+ getGender() +"E-mail:" + getEmail() +"CPF: " + getCpf() + "\nEndereço: "+ getAddress().toString();
    }


    public void retrieveClient(){}
}
