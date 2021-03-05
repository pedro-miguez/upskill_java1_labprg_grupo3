package grupo3.sprint_api.dto;

/**
 *
 * @author Grupo 3
 */
public class RoleDTO {


    public String rolenames;

    public String descricao;



    public RoleDTO() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getRolenames() {
        return rolenames;
    }

    public void setRolenames(String rolename) {
        this.rolenames = rolename;
    }

}