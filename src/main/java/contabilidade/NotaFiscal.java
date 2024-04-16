package contabilidade;

import entities.Atendente;
import entities.Cliente;

import java.time.LocalDateTime;
import java.util.List;

public class NotaFiscal {

    private List<Pedido> pedidosNota;
    private Cliente cliente;
    private Atendente atendente;
    private double valorTotal;
    private LocalDateTime horaGerada;

    public NotaFiscal(){}
    public NotaFiscal(List<Pedido> pedidosNota, Cliente cliente, Atendente atendente, double valorTotal, LocalDateTime horaGerada) {
        this.pedidosNota = pedidosNota;
        this.cliente = cliente;
        this.atendente = atendente;
        this.valorTotal = valorTotal;
        this.horaGerada = horaGerada;
    }

    public List<Pedido> getPedidosNota() {
        return pedidosNota;
    }

    public void setPedidosNota(List<Pedido> pedidosNota) {
        this.pedidosNota = pedidosNota;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Atendente getAtendente() {
        return atendente;
    }

    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDateTime getHoraGerada() {
        return horaGerada;
    }

    public void setHoraGerada(LocalDateTime horaGerada) {
        this.horaGerada = horaGerada;
    }
}
