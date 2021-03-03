package grupo3.sprint_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;


import java.util.ArrayList;

@JsonRootName("roles")

public class ListaRoleDTO {

        @JsonUnwrapped(enabled = false)
        @JsonProperty("role")
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
