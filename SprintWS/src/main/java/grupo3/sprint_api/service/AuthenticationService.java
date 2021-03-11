package grupo3.sprint_api.service;

import grupo3.sprint_api.domain.Context;
import grupo3.sprint_api.domain.Session;
import grupo3.sprint_api.domain.User;
import grupo3.sprint_api.dto.ContextDTO;
import grupo3.sprint_api.dto.LoginDTO;
import grupo3.sprint_api.dto.Mapper;
import grupo3.sprint_api.dto.SessionDTO;
import grupo3.sprint_api.exception.AppKeyInvalidaException;
import grupo3.sprint_api.exception.ConversaoException;
import grupo3.sprint_api.persistence.RepositorioAuth;
import grupo3.sprint_api.persistence.RepositorioUtilizador;

import java.sql.SQLException;

/**
 * Service class which will bridge the controllers
 * and repository data of the authentications.
 */
public class AuthenticationService {

    /**
     * Creates an context.
     *
     * @param appKey
     * @return contextDTO
     * @throws SQLException
     */
    public static ContextDTO generateContext(String appKey) throws SQLException {
        Context context = null;

        try {
            context = new Context(appKey);
        } catch (AppKeyInvalidaException apkex) {
            return null;
        }

        ContextDTO contextDTO = Mapper.context2ContextDTO(context);

        if (contextDTO != null) {
            RepositorioAuth.getInstance().insertContext(context);
            return contextDTO;
        } else throw new ConversaoException("ContextDTO");
    }

    /**
     * Boolean method that checks if the context was validated.
     *
     * @param contextDTO
     * @return boolean
     * @throws SQLException
     */
    public static boolean validateContext(ContextDTO contextDTO) throws SQLException {
        Context context = RepositorioAuth.getInstance().getContextByString(contextDTO.getAppContext());

        return context.isValid();
    }

    /**
     * Boolean method that checks if the login was processed with success.
     *
     * @param loginDTO
     * @param contextDTO
     * @return boolean
     * @throws SQLException
     */
    public static boolean login (LoginDTO loginDTO, ContextDTO contextDTO) throws SQLException {
        User user = RepositorioUtilizador.getInstance().getUtilizadorByNome(loginDTO.getUsername());

        if (!(user != null && user.getPassword().equals(loginDTO.getPassword()))) {
            return false;
        }

        Context context = RepositorioAuth.getInstance().getContextByString(contextDTO.getAppContext());
        Session session = new Session(user, context);
        SessionDTO sessionDTO = Mapper.session2SessionDTO(session);

        if (sessionDTO != null) {
            return RepositorioAuth.getInstance().insertSession(session);
        } else throw new ConversaoException("ContextDTO");
    }

    /**
     * Boolean method that checks if the logout was processed with success.
     *
     * @param contextDTO
     * @return boolean
     * @throws SQLException
     */
    public static boolean logout(ContextDTO contextDTO) throws SQLException {
        return RepositorioAuth.getInstance().makeContextInvalid(contextDTO.getAppContext());
    }

    /**
     * Gets the current session.
     *
     * @param contextDTO
     * @return sessionDTO
     * @throws SQLException
     */
    public static SessionDTO getSession(ContextDTO contextDTO) throws SQLException {
        Session session = RepositorioAuth.getInstance().getSessionByContext(contextDTO.getAppContext());

        if (session == null) {
            return null;
        }

        SessionDTO sessionDTO = Mapper.session2SessionDTO(session);
        if (sessionDTO != null) {
            return sessionDTO;
        } else throw new ConversaoException("SessionDTO");
    }

}
