package entities;

public class Garcom extends Funcionario implements Atendente{
    public Garcom(String nome, String telefone, int idade, String sexo, String endereco, String email, String cpf, int idAutorizacao) {
        super(nome, telefone, idade, sexo, endereco, email, cpf, idAutorizacao);
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
