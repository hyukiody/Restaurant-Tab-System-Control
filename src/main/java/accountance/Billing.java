package accountance;

import entities.Attendant;
import entities.Client;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class Billing {

    private List<Request> pedidosNota;
    private Client client;
    private Attendant attendant;
    private double valorTotalNota;
    private LocalDateTime horaGerada;

    public Billing(){}
    public Billing(Client client, Attendant attendant, LocalDateTime horaGerada) {
        List<Request> pedidosNota = new ArrayList<Request>();
        this.client = client;
        this.attendant = attendant;
        this.valorTotalNota = 0;
        this.horaGerada = horaGerada;
    }

    public List<Request> getPedidosNota() {
        return pedidosNota;
    }

    public void adicionarPedido(Request request) {
        this.pedidosNota.add(request);
    }
    public void removerPedido(Request request) {
        this.pedidosNota.remove(request);
    }

    public Client getCliente() {
        return client;
    }

    public void setCliente(Client client) {
        this.client = client;
    }

    public Attendant getAtendente() {
        return attendant;
    }

    public void setAtendente(Attendant attendant) {
        this.attendant = attendant;
    }

    public double getValorTotalNota() {
        return valorTotalNota = this.pedidosNota.stream().mapToDouble(Request::getValorTotalPedido).sum();
    }



    public LocalDateTime getHoraGerada() {
        return horaGerada;
    }

    public void setHoraGerada(LocalDateTime horaGerada) {
        this.horaGerada = horaGerada;
    }
    public String toString(){
        return "Nota Fiscal: " + "Client: " + client.getName() + ", Attendant: " + attendant.getNome() + ", Valor Total: " + valorTotalNota + ", Hora Gerada: " + horaGerada;
    }
}
