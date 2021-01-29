package domain;

public class Telefone {

    private int number;
    
    public Telefone() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
    }
    
    public Telefone(int number){
        setTelefone(number);
    }

    public int getTelefone() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
