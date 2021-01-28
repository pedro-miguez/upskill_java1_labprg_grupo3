package domain;

public class NIF {

    private int nif;

    public NIF(int nif) {
        setNIF(nif);

    }

    private void setNIF(int nif) {

        if (nif < 100000000 || nif > 999999999) {
            throw new IllegalArgumentException("Nif invalido");
        } else {
            this.nif = nif;
        }
    }
    
    public int getNif(){
        return this.nif;
    }
    
}
