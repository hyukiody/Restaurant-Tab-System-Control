package contabilidade;

import entities.Atendente;
import entities.Cliente;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class NotaFiscal {

    private List<Pedido> pedidosNota;
    private Cliente cliente;
    private Atendente atendente;
    private double valorTotalNota;
    private LocalDateTime horaGerada;

    public NotaFiscal(){}
    public NotaFiscal( Cliente cliente, Atendente atendente, LocalDateTime horaGerada) {
        List<Pedido> pedidosNota = new ArrayList<Pedido>();
        this.cliente = cliente;
        this.atendente = atendente;
        this.valorTotalNota = 0;
        this.horaGerada = horaGerada;
    }

    public List<Pedido> getPedidosNota() {
        return pedidosNota;
    }

    public void adicionarPedido(Pedido pedido) {
        this.pedidosNota.add(pedido);
    }
    public void removerPedido(Pedido pedido) {
        this.pedidosNota.remove(pedido);
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

    public double getValorTotalNota() {
        return valorTotalNota = this.pedidosNota.stream().mapToDouble(Pedido::getValorTotalPedido).sum();
    }



    public LocalDateTime getHoraGerada() {
        return horaGerada;
    }

    public void setHoraGerada(LocalDateTime horaGerada) {
        this.horaGerada = horaGerada;
    }
    public String toString(){
        return "Nota Fiscal: " + "Cliente: " + cliente.getNome() + ", Atendente: " + atendente.getNome() + ", Valor Total: " + valorTotalNota + ", Hora Gerada: " + horaGerada;
    }
}
