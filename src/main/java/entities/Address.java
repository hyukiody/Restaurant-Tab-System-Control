package entities;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Address {
    private String streetAndNumber;
    private String block;
    private String city;
    private String cep;

    public Address(String streetAndNumber, String block, String city, String cep) {
        this.streetAndNumber = streetAndNumber;
        this.block = block;
        this.city = city;
        this.cep = cep;
    }

    public Address() {

    }

    public void setStreetAndNumber(String streetAndNumber) {
        this.streetAndNumber = streetAndNumber;
    }

    public String getStreetAndNumber() {
        return streetAndNumber;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getBlock() {
        return block;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCep() {
        return cep;
    }

    @Override
    public String toString() {
        return "Address: " + streetAndNumber + ", " + block + ", " + city + ", " + cep + "\n";
    }

    public static Address newAddress(Scanner scanner) {
        try {
            System.out.println("Digite a rua do novo endereço:");
            String newStreetAndNumber = scanner.nextLine();
            System.out.println("Digite o bairro do novo endereço:");
            String newBlock = scanner.nextLine();
            System.out.println("Digite o CEP do novo endereço:");
            String newCep = scanner.nextLine();
            System.out.println("Digite a cidade do novo endereço:");
            String newCity = scanner.nextLine();
            return new Address(newStreetAndNumber, newBlock, newCity, newCep);
        }catch(InputMismatchException e){
            System.out.println("Entrada invalida; tente novamente.");
            throw new InputMismatchException();
        }catch (Exception e) {
            System.out.println("An error occurred while creating a new address: " + e.getMessage());
            throw new IllegalArgumentException();
        }
    }

    public Address unknownAddress() {
        return new Address("unknown", "unknown", "unknown", "unknown");
    }
}
