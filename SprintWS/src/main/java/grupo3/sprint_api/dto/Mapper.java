package grupo3.sprint_api.dto;

import grupo3.sprint_api.domain.Context;
import grupo3.sprint_api.domain.Session;
import grupo3.sprint_api.domain.User;

public class Mapper {
    public static ContextDTO context2ContextDTO(Context context) {
        ContextDTO contextDTO = new ContextDTO();
        contextDTO.setAppContext(context.getContext());
        return contextDTO;
    }

    //public static Email emailDTO2Email(EmailDTO emailDTO)

    public static UserDTO user2UserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName(user.getUsername());
        userDTO.setEmail(user.getEmail().toString());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(user.getRole().getDescricao());
        return userDTO;
    }

    public static SessionDTO session2SessionDTO(Session session) {
        SessionDTO sessionDTO = new SessionDTO();
        UserDTO userDTO = Mapper.user2UserDTO(session.getUser());
        sessionDTO.setUser(userDTO);
        sessionDTO.setLogindate(session.getDate().toString());

        return sessionDTO;
    }
}
