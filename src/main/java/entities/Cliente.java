package entities;

import contabilidade.NotaFiscal;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa{
    private List<NotaFiscal> historicoCompras;
    public Cliente(){
        super();
        this.historicoCompras = new ArrayList<>();
        }
    public Cliente(String nome,
                   String telefone,
                   int idade,
                   String sexo,
                   String endereco,
                   String email,
                   String cpf) {
    super(nome, telefone, idade, sexo, endereco, email, cpf);
    this.historicoCompras = new ArrayList<>();
    }
}
