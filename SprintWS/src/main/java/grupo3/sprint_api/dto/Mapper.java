package grupo3.sprint_api.dto;

import grupo3.sprint_api.domain.*;

import java.util.ArrayList;

public class Mapper {
    public static ContextDTO context2ContextDTO(Context context) {
        ContextDTO contextDTO = new ContextDTO();
        contextDTO.setAppContext(context.getContext());
        return contextDTO;
    }

    public static UserDTO user2UserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName(user.getUsername());
        userDTO.setEmail(user.getEmail().toString());
        userDTO.setPassword(user.getPassword());

        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRolename(user.getRole().getDescricao());
        roleDTO.setDescricao(user.getRole().getRolename());
        userDTO.setRole(roleDTO);

        return userDTO;
    }

    public static SessionDTO session2SessionDTO(Session session) {
        SessionDTO sessionDTO = new SessionDTO();
        UserDTO userDTO = Mapper.user2UserDTO(session.getUser());
        sessionDTO.setUser(userDTO);
        sessionDTO.setLogindate(session.getDate().toString());

        return sessionDTO;
    }

    public static User userDTO2User(UserDTO userDTO) {
        Role role = new Role(userDTO.getRole().getRolename(), userDTO.getRole().getDescricao());
        return new User(userDTO.getUserName(), userDTO.getPassword(), new Email(userDTO.getEmail()), role);
    }

    public static ListaRoleDTO listaRole2listaRoleDTO(ArrayList<Role> roles) {
        ArrayList<RoleDTO> rolesDTO = new ArrayList<>();
        for (Role role : roles) {
            try {
                RoleDTO roleDTO = role2roleDTO(role);
                rolesDTO.add(roleDTO);
            } catch (NullPointerException e) {
                //nada é adicionado
            }
        }
        ListaRoleDTO listaRoleDTO = new ListaRoleDTO();
        listaRoleDTO.setRoles(rolesDTO);
        return listaRoleDTO;
    }

    public static RoleDTO role2RoleDTO(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRolename(role.getRolename());
        roleDTO.setDescricao(role.getDescricao());
        return roleDTO;
    }


}
