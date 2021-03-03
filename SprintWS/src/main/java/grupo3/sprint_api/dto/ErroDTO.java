package grupo3.sprint_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JsonRootName("erro")
public class ErroDTO {

    @JsonProperty("mensagem")
    private String mensagemErro;

    public ErroDTO(Exception e) {
        mensagemErro = e.getMessage();
        e.printStackTrace();
    }

    public ErroDTO() {
    }

    public String getMensagemErro() {
        return mensagemErro;
    }

    public void setMensagemErro(String mensagemErro) {
        this.mensagemErro = mensagemErro;
    }
}
