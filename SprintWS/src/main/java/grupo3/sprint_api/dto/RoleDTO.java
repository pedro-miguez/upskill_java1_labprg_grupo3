package grupo3.sprint_api.dto;

/**
 *
 * @author Grupo 3
 */
public class RoleDTO {

    public String descricao;

    public String rolename;


    public RoleDTO() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

}