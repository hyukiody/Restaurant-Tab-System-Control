package platforms;

import entities.Client;
import entities.Attendant;

import java.util.List;

public class Table {
    private int numero;
    private Client client;
    private List<Attendant> attendants;

    public Table() {
        this.client = null;
        this.attendants = null;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void addClient(Client client) {
        this.client = client;
    }

    public void removeClient() {
        this.client = null;
    }

    public List<Attendant> getAttendants() {
        return attendants;
    }

    public void setAttendants(List<Attendant> attendants) {
        this.attendants = attendants;
    }

    public void addAttendants(Attendant attendant) {
        if (!this.attendants.contains(attendant)) {
            this.attendants.add(attendant);
        } else {
            System.out.println("Garçom já adicionado");
        }
    }

    public void removeAttendants(Attendant attendant) {
        if (this.attendants.contains(attendant)) {
            this.attendants.remove(attendant);
        } else {
            System.out.println("Garçom não encontrado");
        }
    }

    public void viewTableInfo() {
        System.out.println("Informações da Mesa n°:  " + this.getNumero() + " \nCliente da mesa: " + this.client.getName() + "\nGarçons da mesa: " + waitersListToString() + "\n");
        ;
    }

    public String waitersListToString() {
        String waiters = "";
        for (Attendant attendant : this.attendants) {
            waiters += attendant.getName() + ", ";
        }
        return waiters;
    }

    public String getTableInfo() {
        return "Cliente da mesa: " + this.client.getName() + "\nGarçons da mesa: " + waitersListToString();
    }

}
