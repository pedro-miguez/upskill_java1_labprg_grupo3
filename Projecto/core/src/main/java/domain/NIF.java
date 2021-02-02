package domain;

import java.io.Serializable;
import java.util.Objects;

public class NIF implements Serializable {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NIF nif1 = (NIF) o;
        return getNif() == nif1.getNif();
    }

}
