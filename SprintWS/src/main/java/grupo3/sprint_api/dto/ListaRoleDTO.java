package grupo3.sprint_api.dto;


import java.util.ArrayList;

public class ListaRoleDTO {

        private ArrayList<RoleDTO> roles;

        public ListaRoleDTO() {
        }

        public ArrayList<RoleDTO> getRoles() {
            return roles;
        }

        public void setRoles(ArrayList<RoleDTO> roles) {
            this.roles = roles;
        }

}
