package accountance;

public class Adress {
    private String streetAndNumber;
    private String block;
    private String city;
    private String cep;

    public Adress(String streetAndNumber, String block, String city, String cep) {
        this.streetAndNumber = streetAndNumber;
        this.block = block;
        this.city = city;
        this.cep = cep;
    }
    public void setStreetAndNumber(String streetAndNumber) {
        this.streetAndNumber = streetAndNumber;
    }
    public String getStreetAndNumber() {
        return streetAndNumber;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getCity() {
        return city;
    }
    public void setBlock(String block) {
        this.block = block;
    }
    public String getBlock() {
        return block;
    }
    public void setCep(String cep) {
        this.cep=cep;
    }
    public String getCep() {
        return cep;
    }
}
