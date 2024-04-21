package accountance;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import entities.Waiter;
import platforms.Table;

public class Request {
    private List<RequestItems> itensDoPedido;

    private Table table;
    private Waiter garcom;
    private double valorPedido;
    private LocalDateTime horaEfetuado;
    private LocalDateTime horaEntregue;

    public Request() {
    }

    public Request(Table table, Waiter garcom, LocalDateTime horaEfetuado, LocalDateTime horaEntregue) {
        List<RequestItems> itensDoPedido = new ArrayList<RequestItems>();
        this.table = table;
        this.garcom = garcom;
        this.horaEfetuado = horaEfetuado;
        this.horaEntregue = horaEntregue;
    }

    public List<RequestItems> getItensDoPedido() {
        return itensDoPedido;
    }

    public void adicionarAoPedido(RequestItems requestItems) {
        this.itensDoPedido.add(requestItems);
    }
    public void removerDoPedido(RequestItems requestItems) {
        this.itensDoPedido.remove(requestItems);
    }

    public Table getMesa() {
        return table;
    }

    public void setMesa(Table table) {
        this.table = table;
    }

    public Waiter getGarcom() {
        return garcom;
    }

    public void setGarcom(Waiter garcom) {
        this.garcom = garcom;
    }

    public double getValorTotalPedido() {
        return valorPedido;
    }
    public void setValorTotalPedido(double valorPedido) {
        this.valorPedido = this.itensDoPedido.stream().mapToDouble(RequestItems::getValorTotalItem).sum();
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
