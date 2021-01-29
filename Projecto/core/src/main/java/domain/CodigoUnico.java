package domain;

public class CodigoUnico {

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
            throw new IllegalArgumentException("Código único inválido, deve ser do tipo 'PRG-01'.");
        }
    }

    public String toString(){
        return String.format("%s", this.codigoUnico);
    }

}
