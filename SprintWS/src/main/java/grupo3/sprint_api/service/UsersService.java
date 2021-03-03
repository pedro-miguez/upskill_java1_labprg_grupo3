package grupo3.sprint_api.service;

import grupo3.sprint_api.domain.Email;
import grupo3.sprint_api.domain.Role;
import grupo3.sprint_api.domain.User;
import grupo3.sprint_api.dto.UserDTO;
import grupo3.sprint_api.exception.NomeNaoAssociadoException;
import grupo3.sprint_api.persistence.RepositorioUtilizador;

public class UsersService {

    public static UserDTO getUtilizador(Email email) {
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

    public static void registerUser(String username, String password, Email email) {
        User user = new User(username, password, email);
        if (user != null) {
            RepositorioUtilizador repo = RepositorioUtilizador.getInstance();
            repo.insertUtilizador(user);
        } else {
            throw new ConversaoException("UserDTO");
        }
    }

    public static void registerUserWithRoles(String username, String password, Email email, Role role) {
        User user = new User(username, password, email, role);
        if (user != null) {
            RepositorioUtilizador repo = RepositorioUtilizador.getInstance();
            repo.insertUtilizador(user);
        } else {
            throw new ConversaoException("UserDTO");
        }
    }

    /*public boolean login(String username, String password) {
        try {
            RepositorioUtilizador repo = RepositorioUtilizador.getInstance();
            User user = repo.getUtilizadorByNome().getPassword();
            if (password.equalsIgnoreCase(password)) {
                return true;
            } catch(IllegalArgumentException ex){
                throw new NomeNaoAssociadoException("Username ou password errados");
            }
        }

    }*/
}