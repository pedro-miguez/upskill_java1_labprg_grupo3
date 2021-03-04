package grupo3.sprint_api.service;

import grupo3.sprint_api.domain.Email;
import grupo3.sprint_api.domain.Role;
import grupo3.sprint_api.domain.User;
import grupo3.sprint_api.dto.ListaRoleDTO;
import grupo3.sprint_api.dto.Mapper;
import grupo3.sprint_api.dto.RoleDTO;
import grupo3.sprint_api.dto.UserDTO;
import grupo3.sprint_api.exception.ConversaoException;
import grupo3.sprint_api.persistence.RepositorioUtilizador;

import java.sql.SQLException;
import java.util.ArrayList;

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

    public static void registerUser(String username, String password, Email email) throws SQLException {
        User user = new User(username, password, email);
        if (user != null) {
            RepositorioUtilizador repo = RepositorioUtilizador.getInstance();
            repo.insertUtilizador(user);
        } else {
            throw new ConversaoException("UserDTO");
        }
    }

    public static void registerUserWithRoles(String username, String password, Email email, Role role) throws SQLException {
        User user = new User(username, password, email, role);
        if (user != null) {
            RepositorioUtilizador repo = RepositorioUtilizador.getInstance();
            repo.insertUtilizador(user);
        } else {
            throw new ConversaoException("UserDTO");
        }
    }


    public static ListaRoleDTO getRoles() {
        throw new UnsupportedOperationException();
    }

    public static RoleDTO getUserRoles(String username) {
        throw new UnsupportedOperationException();
    }

    public static void addRoleToUser(String username, String rolename) {
        throw new UnsupportedOperationException();
    }

    public static void createUserRole(String rolename, String description) {
        throw new UnsupportedOperationException();
    }

    public static void deleteUserRole(String rolename) {
        throw new UnsupportedOperationException();
    }

    public static void deleteRoleFromUser(String username, String rolename) {
        throw new UnsupportedOperationException();
    }
}
