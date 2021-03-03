package grupo3.sprint_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonPropertyOrder({"descricao","designacao"})
@JsonRootName("role")

/**
 *
 * @author Grupo 3
 */
public class RoleDTO {


    @JsonProperty("descricao")
    private String descricao;

    @JsonProperty("designacao")
    private String designacao;


    public RoleDTO() {
        super();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

}