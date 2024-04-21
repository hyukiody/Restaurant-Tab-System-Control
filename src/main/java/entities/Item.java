package entities;
import java.util.List;
import java.util.Scanner;

public class Item {
    private String name;
    private List<String> availableDays;
    private double valorUnitario;
    private int idItem;

    public Item(String name, double valorUnitario, int idItem) {
        this.name = name;
        this.valorUnitario = valorUnitario;
        this.idItem=idItem;
    }

     public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public double getSingleValue(){
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario){
        this.valorUnitario=valorUnitario;
    }

    public int getIdItem(){
        return idItem;
    }
    public void setIdItem(int idItem){
        this.idItem=idItem;
    }
    @Override
    public String toString() {
        return "Dados do item :"+ "nome: " + name + "\n" + getToStringAvailableDays() + "\n" + ", Valor da unidade=" + valorUnitario + ", idItem=" + idItem;
    }

    public String getToStringAvailableDays(){
        StringBuilder sb = new StringBuilder();
        sb.append("Dias disponíveis: \n");
        for (String day : availableDays) {
            sb.append(day).append(", ");
        }
        return sb.toString();
    }
    public List<String> getAvailableDays() {
        return availableDays;
    }

    public void setAvailableDays(List<String> availableDays) {
        this.availableDays = availableDays;
    }
    public void chooseToAddAvailableDays(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Escolha os dias disponiveis para o item: ");
        System.out.println("1- Domingo \n 2- Segunda \n 3- Terça \n 4- Quarta \n 5- Quinta \n 6-Sexta \n 7- Sábado");
        int choice=sc.nextInt();
        switch(choice) {
            case 1:
                if(availableDays.contains("Domingo")!=true){
                availableDays.add("Domingo");
                break;
                }
                else{
                    System.out.println("Dia já adicionado");
                }
            case 2:
                if(availableDays.contains("Segunda")!=true) {
                    availableDays.add("Segunda");
                    break;
                }
                else{
                    System.out.println("Dia já adicionado");
                }
            case 3:
                if(availableDays.contains("Terca")!=true){
                    availableDays.add("Terca");
                    break;
                }
                else{System.out.println("Dia já adicionado");}
            case 4:
                if(availableDays.contains("Quarta")!=true) {
                    availableDays.add("Quarta");
                    break;
                }
                else{System.out.println("Dia já adicionado");}
            case 5:
                if(availableDays.contains("Quinta")!=true) {
                    availableDays.add("Quinta");
                    break;
                }
                else{System.out.println("Dia já adicionado");}
            case 6:
                if(availableDays.contains("Sexta")!=true) {
                    availableDays.add("Sexta");
                    break;
                }
                else{System.out.println("Dia já adicionado");}
            case 7:
                if(availableDays.contains("Sabado")!=true) {
                    availableDays.add("Sabado");
                    break;
                }
                else{System.out.println("Dia já adicionado");}
        }
        sc.close();

    }
}
