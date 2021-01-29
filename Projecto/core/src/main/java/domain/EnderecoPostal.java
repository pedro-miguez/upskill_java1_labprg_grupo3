package domain;

public class EnderecoPostal {

    private String local;
    private String localidade;
    private String codPostal;


    public EnderecoPostal(String local, String localidade, String codPostal) {
        setLocal(local);
        setLocalidade(localidade);
        setCodPostal(codPostal);
    }

    public String getLocal() {
        return local;
    }

    public String getLocalidade() {
        return localidade;
    }

    public String getCodPostal() {
        return codPostal;
    }

    private void setLocal(String local) {
        this.local = local;
    }

    private void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    private void setCodPostal(String codPostal) {
        if (codPostal.matches("^\\d{4}(-\\d{3})?$")) {
            this.codPostal = codPostal;
        } else {
            throw new IllegalArgumentException("O código postal introduzido não é valido");
        }
    }

    @Override
    public String toString() {
        return String.format("Rua: %s, %s, com o código-postal %s", getLocal(), getLocalidade(), getCodPostal());
    }
}
