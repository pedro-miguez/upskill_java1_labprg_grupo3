package domain;

import java.io.Serializable;
import java.util.Objects;

public class CodigoUnico implements Serializable {

    private String codigoUnico;

    public CodigoUnico(String codigoUnico) {
        setCodigoUnico(codigoUnico);
    }

    public String getCodigoUnico() {
        return codigoUnico;
    }

    private void setCodigoUnico(String codigoUnico) {
        if (codigoUnico.matches("^([a-zA-Z]){3}(-\\d{2})?$")) {
            this.codigoUnico = codigoUnico;
        } else {
            throw new IllegalArgumentException("Código único inválido, deve ser como o seguinte exemplo, 'PRG-01'.");
        }
    }

    public String toString(){
        return String.format("%s", this.codigoUnico);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodigoUnico that = (CodigoUnico) o;
        return getCodigoUnico().equals(that.getCodigoUnico());
    }

}
