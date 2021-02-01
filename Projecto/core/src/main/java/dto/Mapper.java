package dto;

import domain.Email;
import domain.User;
import java.util.ArrayList;

public class Mapper {


    public static UserDTO user2userDTO(User user) throws NullPointerException {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail().toString());

        return userDTO;
    }

   /* public static User userDTO2User(UserDTO userDTO) throws NullPointerException {
        return new User(userDTO.getUsername(), userDTO.getPassword(), new Email(userDTO.getEmail()));
    }*/

}
