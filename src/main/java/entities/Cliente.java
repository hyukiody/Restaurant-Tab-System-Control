package entities;

import contabilidade.notaFiscal;

import java.util.ArrayList;

public class Cliente extends Pessoa{

    private List<notaFiscal> historicoCompras;

    public Cliente(String nome, String telefone, int idade, String sexo, String endereco, String email, String cpf) {
        super();
        this.historicoCompras = new ArrayList<notaFiscal>();

    }
}
