package entities;

import contabilidade.Endereco;

public abstract class Funcionario extends Pessoa {
    private int idAutorizacao;

    public Funcionario(String nome, String telefone, int idade, String sexo, String email, String cpf,Endereco endereco, int idAutorizacao) {
        super(nome,  telefone,  idade, sexo, email,  cpf, endereco);
        this.idAutorizacao = idAutorizacao;
    }

    public int getIdAutorizacao() {
        return idAutorizacao;
    }

    public void setIdAutorizacao(int idAutorizacao) {
        this.idAutorizacao = idAutorizacao;
    }
}
