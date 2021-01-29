package domain;

public class Telefone {

    private int number;

    public Telefone(int number){
        setTelefone(number);
    }

    public int getTelefone() {
        return this.number;
    }
    
    private void setTelefone(int number) {
        if (number < 200000000 || number > 999999999) {
            throw new IllegalArgumentException("Número de telefone inválido");
        } else {
            this.number = number;
        }
    }

}
