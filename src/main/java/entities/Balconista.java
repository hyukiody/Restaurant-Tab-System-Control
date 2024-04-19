package entities;

import contabilidade.Endereco;

public class Balconista extends Funcionario implements Atendente {
    public Balconista(String nome, String telefone, int idade, String sexo, String email, String cpf, Endereco endereco, int idAutorizacao) {
        super(nome, telefone, idade, sexo, email, cpf, endereco, idAutorizacao);
    }

    @Override
    public void realizarPedido() {
        System.out.println("Pedido realizado");
    }

    @Override
    public void agendarPedido() {
        System.out.println("Pedido agendado");
    }

    @Override
    public void entregarPedido() {
        System.out.println("Pedido entregue");
    }
}
