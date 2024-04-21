package accountance;

import entities.Attendant;
import entities.Client;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class Billing {

    private List<Order> pedidosNota;
    private Client client;
    private Attendant attendant;
    private double valorTotalNota;
    private LocalDateTime horaGerada;

    public Billing(){}
    public Billing(Client client, Attendant attendant, LocalDateTime horaGerada) {
        List<Order> pedidosNota = new ArrayList<Order>();
        this.client = client;
        this.attendant = attendant;
        this.valorTotalNota = 0;
        this.horaGerada = horaGerada;
    }

    public List<Order> getPedidosNota() {
        return pedidosNota;
    }

    public void adicionarPedido(Order order) {
        this.pedidosNota.add(order);
    }
    public void removerPedido(Order order) {
        this.pedidosNota.remove(order);
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
        return valorTotalNota = this.pedidosNota.stream().mapToDouble(Order::getValorTotalPedido).sum();
    }



    public LocalDateTime getHoraGerada() {
        return horaGerada;
    }

    public void setHoraGerada(LocalDateTime horaGerada) {
        this.horaGerada = horaGerada;
    }
    public String toString(){
        return "Nota Fiscal: " + "Client: " + client.getName() + ", Attendant: " + attendant.getName() + ", Valor Total: " + valorTotalNota + ", Hora Gerada: " + horaGerada;
    }
}
