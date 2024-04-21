package platforms;

import accountance.Billing;
import entities.Client;
import entities.Waiter;

import java.util.List;

public class Table {
    private Billing billing;
    private Client client;
    private List<Waiter> listaGarcom;

    public Table(){}

    public Table(Billing billing, Client client, List<Waiter> listaGarcom) {
        this.billing = billing;
        this.client = client;
        this.listaGarcom = listaGarcom;
    }
    public void setNotaFiscal(Billing billing) {
        this.billing = billing;
    }
    public Billing getNotaFiscal() {
        return billing;
    }
    public Client getCliente() {
        return client;
    }
    public void setCliente(Client client) {
        this.client = client;
    }
    public List<Waiter> getListaGarcom() {
        return listaGarcom;
    }
    public void setListaGarcom(List<Waiter> listaGarcom) {
        this.listaGarcom = listaGarcom;
    }
    public Billing geraNotaFiscal(){
        return this.billing;
    }
}
