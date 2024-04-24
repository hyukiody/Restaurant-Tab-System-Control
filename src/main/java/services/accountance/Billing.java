package services.accountance;

import sets.Attendant;
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
    private String PaymentStatus;

    public Billing(){
        this.client=null;
        this.attendant=null;
        this.valorTotalNota=0;
        this.horaGerada=null;
        this.PaymentStatus = "Paid";
    }
    public Billing(Client client, Attendant attendant, LocalDateTime horaGerada,String PaymentStatus) {
        List<Order> pedidosNota = new ArrayList<Order>();
        this.client = client;
        this.attendant = attendant;
        this.valorTotalNota = 0;
        this.horaGerada = horaGerada;
        this.PaymentStatus = PaymentStatus;
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
    public String getPaymentStatus() {
        return PaymentStatus;
    }
    public void setPaymentStatus(String PaymentStatus) {
        this.PaymentStatus = PaymentStatus;
    }
    public String toString(){
        return "Nota Fiscal: " + "Client: " + client.getName() + ", Attendant: " + attendant.getName() + ", Valor Total: " + valorTotalNota + ", Hora Gerada: " + horaGerada;
    }
}
