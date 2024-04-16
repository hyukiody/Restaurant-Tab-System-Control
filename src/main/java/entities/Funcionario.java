package entities;

public abstract class Funcionario extends Pessoa {
    private int idAutorizacao;

    public Funcionario(String nome, String telefone, int idade, String sexo, String endereco, String email, String cpf, int idAutorizacao) {
        super(nome,  telefone,  idade, sexo, endereco,  email,  cpf);
        this.idAutorizacao = idAutorizacao;
    }
}
