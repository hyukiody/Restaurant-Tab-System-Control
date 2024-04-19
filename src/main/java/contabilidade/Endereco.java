package contabilidade;

public class Endereco {
    private String ruaeNumero;
    private String bairro;
    private String cidade;
    private String cep;

    public Endereco(String ruaeNumero, String bairro, String cidade, String cep) {
        this.ruaeNumero = ruaeNumero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.cep = cep;
    }
    public void setRuaeNumero(String ruaeNumero) {
        this.ruaeNumero = ruaeNumero;
    }
    public String getRuaeNumero() {
        return ruaeNumero;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public String getCidade() {
        return cidade;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    public String getBairro() {
        return bairro;
    }
    public void setCep(String cep) {
        this.cep=cep;
    }
    public String getCep() {
        return cep;
    }
}
