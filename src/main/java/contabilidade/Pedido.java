package contabilidade;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import entities.Garcom;
import plataforma.Mesa;

public class Pedido {
    private List<ItemDoPedido> itensDoPedido;

    private Mesa mesa;
    private Garcom garcom;
    private double valorPedido;
    private LocalDateTime horaEfetuado;
    private LocalDateTime horaEntregue;

    public Pedido() {
    }

    public Pedido(Mesa mesa, Garcom garcom, LocalDateTime horaEfetuado, LocalDateTime horaEntregue) {
        List<ItemDoPedido> itensDoPedido = new ArrayList<ItemDoPedido>();
        this.mesa = mesa;
        this.garcom = garcom;
        this.horaEfetuado = horaEfetuado;
        this.horaEntregue = horaEntregue;
    }

    public List<ItemDoPedido> getItensDoPedido() {
        return itensDoPedido;
    }

    public void adicionarAoPedido(ItemDoPedido itemDoPedido) {
        this.itensDoPedido.add(itemDoPedido);
    }
    public void removerDoPedido(ItemDoPedido itemDoPedido) {
        this.itensDoPedido.remove(itemDoPedido);
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

    public double getValorTotalPedido() {

        return valorPedido;
    }
    public void setValorTotalPedido(double valorPedido) {
        this.valorPedido = this.itensDoPedido.stream().mapToDouble(ItemDoPedido -> ItemDoPedido.getValorTotal()).sum();
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

}
