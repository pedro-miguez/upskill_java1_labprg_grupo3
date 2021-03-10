package grupo3.sprint_api.dto;


import java.util.ArrayList;

public class ListaRoleDTO {

    private ArrayList<RoleDTO> roles;

    /**
     * Represents an empty constructor.
     */
    public ListaRoleDTO() {
        }

    /**
     * Gets an list of 'Roles'.
     *
     * @return roles
     */
    public ArrayList<RoleDTO> getRoles() {
            return roles;
        }

    /**
     * Sets an list of 'roles'.
     *
     * @param roles
     */
    public void setRoles(ArrayList<RoleDTO> roles) {
            this.roles = roles;
        }

}
