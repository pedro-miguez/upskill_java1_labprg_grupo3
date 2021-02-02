package domain;

import java.io.Serializable;
import java.util.Objects;

public class Telefone implements Serializable {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Telefone telefone = (Telefone) o;
        return number == telefone.number;
    }

}
