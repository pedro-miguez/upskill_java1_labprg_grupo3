package grupo3.sprint_api.service;

import grupo3.sprint_api.domain.Email;
import grupo3.sprint_api.domain.User;
import grupo3.sprint_api.dto.UserDTO;
import grupo3.sprint_api.persistence.RepositorioUtilizador;
import com.company.dto.ListaPessoaDTO;
import com.company.dto.Mapper;
import com.company.dto.PessoaDTO;
import com.company.exception.ConversaoException;
import com.company.model.Autarquia;
import com.company.model.Pessoa;
import java.util.ArrayList;

public class UsersService {

    public static ListaUserDTO getUsers() {
        ListaUserDTO listaUserDTO = null;
        RepositorioUtilizador repo = RepositorioUtilizador.getInstance();
        ArrayList<User> users = repo.listarUtilizadores();
        ListaUserDTO = Mapper.listUser2UserDTO(users);
        return listaPessoaDTO;
    }

    public static UserDTO getUser(Email email) {
        RepositorioUtilizador repo = RepositorioUtilizador.getInstance();
        User user = repo.getUserByEmail(email);
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

    public static void addUser(UserDTO userDTO) {
        User user = Mapper.userDTO2User(userDTO);
        if (user != null) {
            RepositorioUtilizador repo = RepositorioUtilizador.getInstance();
            repo.addUtilizador(user);
            RepositorioUtilizador.insertUtilizador(user);
        } else {
            throw new ConversaoException("PessUserDTOoaDTO");
        }
    }


}
