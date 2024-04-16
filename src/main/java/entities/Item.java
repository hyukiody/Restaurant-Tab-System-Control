package entities;

public class Item {
    private String nome;
    private String diaDisponivel;
    private double valorUnitario;
    private int idItem;

    public Item(String nome, String diaDisponivel, double valorUnitario, int idItem) {
        this.nome = nome;
        this.diaDisponivel = diaDisponivel;
        this.valorUnitario = valorUnitario;
        this.idItem=idItem;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome){
        this.nome=nome;
    }
    public void setDiaDisponivel(String diaDisponivel){
        this.diaDisponivel=diaDisponivel;
    }
    public String getDiaDisponivel(){
        return diaDisponivel;
    }
    public void setValorUnitario(double valorUnitario){
        this.valorUnitario=valorUnitario;
    }
    public double getValorUnitario(){
        return valorUnitario;
    }
    public int getIdItem(){
        return idItem;
    }
    public void setIdItem(int idItem){
        this.idItem=idItem;
    }
    @Override
    public String toString() {
        return "Item :'"+ "nome: " + nome + '\'' + ", diaDisponivel='" + diaDisponivel + '\'' + ", valorUnitario=" + valorUnitario + ", idItem=" + idItem;
    }
}
