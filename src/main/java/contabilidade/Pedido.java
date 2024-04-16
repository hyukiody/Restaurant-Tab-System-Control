package contabilidade;
import java.time.LocalDateTime;
import java.util.List;

import entities.Garcom;
import plataforma.Mesa;
import entities.Item;
public class Pedido {
    private List<Item> itensPedido;

    private Mesa mesa;
    private Garcom garcom;
    private double valorTotal;

    public List<Item> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<Item> itensPedido) {
        this.itensPedido = itensPedido;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public Garcom getGarcom() {
        return garcom;
    }

    public void setGarcom(Garcom garcom) {
        this.garcom = garcom;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDateTime getHoraEfetuado() {
        return horaEfetuado;
    }

    public void setHoraEfetuado(LocalDateTime horaEfetuado) {
        this.horaEfetuado = horaEfetuado;
    }

    public LocalDateTime getHoraEntregue() {
        return horaEntregue;
    }

    public void setHoraEntregue(LocalDateTime horaEntregue) {
        this.horaEntregue = horaEntregue;
    }

    private LocalDateTime horaEfetuado;
    private LocalDateTime horaEntregue;

    public Pedido(){}

    public Pedido(List<Item> itensPedido, Mesa mesa, Garcom garcom, double valorTotal, LocalDateTime horaEfetuado, LocalDateTime horaEntregue) {
        this.itensPedido = itensPedido;
        this.mesa = mesa;
        this.garcom = garcom;
        this.valorTotal = valorTotal;
        this.horaEfetuado = horaEfetuado;
        this.horaEntregue = horaEntregue;
    }

}
