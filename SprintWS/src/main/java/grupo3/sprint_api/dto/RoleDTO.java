package grupo3.sprint_api.dto;

/**
 * This represents a "clone" of the model (domain) class 'Role'.
 * (Because model classes cannot be exposed to outside)
 * This will be used for serialization and deserialization of the class Role.
 *
 * @author Grupo 3
 */
public class RoleDTO {


    public String rolenames;

    public String descricao;


    /**
     * Represents an empty constructor.
     */
    public RoleDTO() {
    }

    /**
     * Gets an description.
     *
     * @return descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Sets an description.
     *
     * @param descricao
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Gets the names of the roles.
     *
     * @return rolenames
     */
    public String getRolenames() {
        return rolenames;
    }

    /**
     * Sets the names of the roles.
     *
     * @param rolename
     */
    public void setRolenames(String rolename) {
        this.rolenames = rolename;
    }

}