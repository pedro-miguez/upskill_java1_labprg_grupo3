package grupo3.sprint_api.dto;

public class ErroDTO {

    private String mensagemErro;

    /**
     * Represents the constructor of the class 'ErroDTO'.
     *
     * @param e
     */
    public ErroDTO(Exception e) {
        mensagemErro = e.getMessage();
        e.printStackTrace();
    }

    /**
     * Represents an empty constructor.
     */
    public ErroDTO() {
    }

    /**
     * Gets an error message.
     *
     * @return mensagemErro
     */
    public String getMensagemErro() {
        return mensagemErro;
    }

    /**
     * Sets an error message.
     *
     * @param mensagemErro
     */
    public void setMensagemErro(String mensagemErro) {
        this.mensagemErro = mensagemErro;
    }
}
