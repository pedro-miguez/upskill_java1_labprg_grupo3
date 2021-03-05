package grupo3.sprint_api.service;

import grupo3.sprint_api.domain.Email;
import grupo3.sprint_api.domain.Role;
import grupo3.sprint_api.domain.User;
import grupo3.sprint_api.dto.ListaRoleDTO;
import grupo3.sprint_api.dto.Mapper;
import grupo3.sprint_api.dto.RoleDTO;
import grupo3.sprint_api.dto.UserDTO;
import grupo3.sprint_api.exception.ConversaoException;
import grupo3.sprint_api.persistence.RepositorioRole;
import grupo3.sprint_api.persistence.RepositorioUtilizador;

import java.sql.SQLException;
import java.util.ArrayList;
import grupo3.sprint_api.dto.UserDTO;
import grupo3.sprint_api.exception.NomeNaoAssociadoException;
import grupo3.sprint_api.persistence.RepositorioUtilizador;

/**
 * Service class which will bridge the controllers and repository data of the users.
 */
public class UsersService {

    public static UserDTO getUtilizador(Email email) throws SQLException {
        RepositorioUtilizador repo = RepositorioUtilizador.getInstance();
        User user = repo.getUtilizadorByEmail(email);
        if (user == null) {
            return null;
        }
        UserDTO userDTO = Mapper.user2UserDTO(user);
        if (userDTO != null) {
            return userDTO;
        } else {
            throw new ConversaoException("UserDTO");

        }
    }

    public static void registerUser(UserDTO userDTO) throws SQLException {
        User user = Mapper.userDTO2User(userDTO);
        if (user != null) {
            RepositorioUtilizador repo = RepositorioUtilizador.getInstance();
            repo.insertUtilizadorSemRole(user);
        } else {
            throw new ConversaoException("UserDTO");
        }
    }


    public static void registerUserWithRoles(UserDTO userDTO, String rolename) throws SQLException {
        User user = Mapper.userDTO2User(userDTO);
        if (user != null) {
            RepositorioRole repoRole = RepositorioRole.getInstance();
            Role role = repoRole.getRoleByRolename(rolename);
            user.setRole(role);
            RepositorioUtilizador repo = RepositorioUtilizador.getInstance();
            repo.insertUtilizadorComRole(user);
        } else {
            throw new ConversaoException("UserDTO");
        }
    }


    public static ListaRoleDTO getRoles() throws SQLException {
        ListaRoleDTO listaRoleDTO = null;
        RepositorioRole repoRole = RepositorioRole.getInstance();
        ArrayList<Role> roles = repoRole.getRoles();
        listaRoleDTO = Mapper.listaRole2listaRoleDTO(roles);

        return listaRoleDTO;

    }

    
    public static RoleDTO getUserRoles(String username) throws SQLException {
        RepositorioUtilizador repoUser = RepositorioUtilizador.getInstance();
        User user = repoUser.getUtilizadorByNome(username);
        Role role = user.getRole();
        RoleDTO roleDTO = Mapper.role2RoleDTO(role);
        if (roleDTO != null) {
            return roleDTO;
        } else {
            throw new ConversaoException("RoleDTO");
        }
    }

    public static boolean addRoleToUser(String username, String rolename) throws Exception {
        RepositorioUtilizador repoUser = RepositorioUtilizador.getInstance();
        User user = repoUser.getUtilizadorByNome(username);
        if (user == null) {
            throw new NomeNaoAssociadoException("User não encontrado");
        }
        RepositorioRole repoRole = RepositorioRole.getInstance();
        Role role = repoRole.getRoleByRolename(rolename);
        user.setRole(role);
        if (role == null) {
            throw new Exception("Problema ao adicionar o Role ao utilizador " + username);
        }
        return repoUser.addRoleToUser(user);
    }

    public static void createUserRole(RoleDTO roleDTO) throws Exception {
        RepositorioRole repoRole = RepositorioRole.getInstance();
        Role role = Mapper.roleDTO2Role(roleDTO);
        ArrayList<Role> roles = repoRole.getRoles();
        if (!roles.contains(role)) {
            repoRole.insertRole(role);
        } else {
            throw new Exception("Erro ao adicionar role");
        }
    }

    public static void deleteUserRole(String rolename) throws Exception {
        RepositorioRole repoRole = RepositorioRole.getInstance();
        ArrayList<Role> roles = repoRole.getRoles();
        Role role = repoRole.getRoleByRolename(rolename);
        if (roles.contains(role)) {
            repoRole.deleteRole(role);
        } else {
            throw new Exception("Problema ao apagar role");
        }
    }

    public static void deleteRoleFromUser(String username, String rolename) throws Exception {
        RepositorioUtilizador repoUser = RepositorioUtilizador.getInstance();
        User user = repoUser.getUtilizadorByNome(username);

        RepositorioRole repoRole = RepositorioRole.getInstance();
        Role role = repoRole.getRoleByRolename(rolename);

        if (user.getRole().equals(role)) {
            repoUser.deleteRoleFromUser(user);
        } else {
            throw new Exception("Problma ao apagar role do utilizador " + username);
        }

    }

}