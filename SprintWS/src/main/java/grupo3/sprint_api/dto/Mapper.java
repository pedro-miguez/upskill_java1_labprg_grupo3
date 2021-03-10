package grupo3.sprint_api.dto;

import grupo3.sprint_api.domain.*;

import java.util.ArrayList;

/**
 * This class is responsible for conversions that need to happen between
 * the internal entities of a Spring application and the external DTOs
 * (Data Transfer Objects) that are published back to the client.
 *
 * It represents the conversion from Entity to DTO, and from DTO to Entity
 * in a Spring REST API, by using the model mapper library.
 */
public class Mapper {

    /**
     * Method that represents the conversion of Context to ContextDTO.
     *
     * @param context
     * @return contextDTO
     */
    public static ContextDTO context2ContextDTO(Context context) {
        ContextDTO contextDTO = new ContextDTO();
        contextDTO.setAppContext(context.getContext());
        return contextDTO;
    }

    /**
     * Method that represents the conversion of User to UserDTO.
     *
     * @param user
     * @return userDTO
     */
    public static UserDTO user2UserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail().toString());
        userDTO.setPassword(user.getPassword());

        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setDescricao(user.getRole().getDescricao());
        roleDTO.setRolenames(user.getRole().getRolename());
        userDTO.setRole(roleDTO);

        return userDTO;
    }

    /**
     * Method that represents the conversion of Session to SessionDTO.
     *
     * @param session
     * @return sessionDTO
     */
    public static SessionDTO session2SessionDTO(Session session) {
        SessionDTO sessionDTO = new SessionDTO();
        UserDTO userDTO = Mapper.user2UserDTO(session.getUser());
        sessionDTO.setUser(userDTO);
        sessionDTO.setLogindate(session.getDate().toString());

        return sessionDTO;
    }

    /**
     * Method that represents the conversion of UserDTO to User.
     *
     * @param userDTO
     * @return User
     */
    public static User userDTO2User(UserDTO userDTO) {
        if (userDTO.getRole() != null) {
            Role role = new Role(userDTO.getRole().getRolenames(), userDTO.getRole().getDescricao());
            return new User(userDTO.getUsername(), userDTO.getPassword(), new Email(userDTO.getEmail()), role);
        } else {
            return new User(userDTO.getUsername(), userDTO.getPassword(), new Email(userDTO.getEmail()));
        }
    }

    /**
     * Method that represents the conversion of ListaRole to ListaRoleDTO.
     *
     * @param roles
     * @return listaRoleDTO
     */
    public static ListaRoleDTO listaRole2listaRoleDTO(ArrayList<Role> roles) {
        ArrayList<RoleDTO> rolesDTO = new ArrayList<>();
        for (Role role : roles) {
            try {
                RoleDTO roleDTO = role2RoleDTO(role);
                rolesDTO.add(roleDTO);
            } catch (NullPointerException e) {
                //nada é adicionado
            }
        }
        ListaRoleDTO listaRoleDTO = new ListaRoleDTO();
        listaRoleDTO.setRoles(rolesDTO);
        return listaRoleDTO;
    }

    /**
     * Method that represents the conversion of Role to RoleDTO.
     *
     * @param role
     * @return roleDTO
     */
    public static RoleDTO role2RoleDTO(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRolenames(role.getRolename());
        roleDTO.setDescricao(role.getDescricao());
        return roleDTO;
    }


    /**
     * Method that represents the conversion of RoleDTO to Role.
     *
     * @param roleDTO
     * @return Role
     */
    public static Role roleDTO2Role(RoleDTO roleDTO) {
        return new Role(roleDTO.getRolenames(), roleDTO.getDescricao());
    }
}
