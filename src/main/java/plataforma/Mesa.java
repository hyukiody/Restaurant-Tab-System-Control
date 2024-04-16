package plataforma;

import contabilidade.NotaFiscal;
import entities.Cliente;
import entities.Garcom;

import java.util.List;

public class Mesa {
    private NotaFiscal notaFiscal;
    private Cliente cliente;
    private List<Garcom> listaGarcom;

    public Mesa(){}

    public Mesa(NotaFiscal notaFiscal, Cliente cliente, List<Garcom> listaGarcom) {
        this.notaFiscal = notaFiscal;
        this.cliente = cliente;
        this.listaGarcom = listaGarcom;
    }
    public void setNotaFiscal(NotaFiscal notaFiscal) {
        this.notaFiscal = notaFiscal;
    }
    public NotaFiscal getNotaFiscal() {
        return notaFiscal;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public List<Garcom> getListaGarcom() {
        return listaGarcom;
    }
    public void setListaGarcom(List<Garcom> listaGarcom) {
        this.listaGarcom = listaGarcom;
    }
    public NotaFiscal geraNotaFiscal(){
        return this.notaFiscal;
    }
}
