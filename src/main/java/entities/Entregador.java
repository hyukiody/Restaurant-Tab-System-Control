package entities;

import contabilidade.Endereco;

public class Entregador extends Funcionario implements Atendente{
    public Entregador(String nome, String telefone, int idade, String sexo, String email, String cpf, Endereco endereco, int idAutorizacao) {
        super(nome, telefone, idade, sexo, email, cpf, endereco, idAutorizacao);
    }

    @Override
    public void realizarPedido() {

    }

    @Override
    public void agendarPedido() {

    }

    @Override
    public void entregarPedido() {

    }
}
