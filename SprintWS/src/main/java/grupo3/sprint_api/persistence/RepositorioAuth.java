package grupo3.sprint_api.persistence;

import grupo3.sprint_api.domain.*;
import grupo3.sprint_api.exception.EmailNaoAssociadoException;
import grupo3.sprint_api.exception.FetchingProblemException;
import grupo3.sprint_api.exception.NomeNaoAssociadoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RepositorioAuth {
    private static RepositorioAuth instance;

    private ConnectionHandler connectionHandler;

    private RepositorioAuth() throws SQLException {
        connectionHandler = new ConnectionHandler();
    }


    /**
     * Static method that returns a unique reference to the class object,
     * that implements a singleton.
     *
     * @return instance
     */


    public static RepositorioAuth getInstance() throws SQLException {
        if(instance == null){
            instance = new RepositorioAuth();
        }
        return instance;
    }


    public boolean insertContext(Context context) {
        //String context
        // boolean valid
        return false;
    }

    public Context getContextByString(String contextString) {
        throw new UnsupportedOperationException();
    }

    public boolean insertSession(Session session) {

        //idutilizador
        //string context
        //localdatetime data atual
        return false;
    }

    public boolean makeContextInvalid(String context) {
        return false;
    }

    public Session getSessionByContext(String contextString) {
        throw new UnsupportedOperationException();
    }
}
