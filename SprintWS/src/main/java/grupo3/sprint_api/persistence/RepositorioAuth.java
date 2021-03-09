package grupo3.sprint_api.persistence;

import grupo3.sprint_api.domain.*;
import grupo3.sprint_api.exception.*;

import java.sql.*;
import java.util.ArrayList;

/**
 * Class responsible for creating a repository to store information about Authentication.
 */
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


    /**
     * Boolean method which checks if the Context was inserted.
     *
     * @param context
     * @return boolean
     */
    public boolean insertContext(Context context) {
        Connection conn = connectionHandler.getConnection();

        try {
            PreparedStatement pstmt = conn.prepareCall("insert into ContextKeys(context_key, estado) values (?, ?)");
            ResultSet rs = null;


            conn.setAutoCommit(false);

            pstmt.setString(1, context.getContext());
            pstmt.setString(2, "VAL");

            pstmt.executeQuery();

            conn.commit();
            pstmt.close();

            return true;
        } catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();
            try {
                System.err.print("Transaction is being rolled back");
                conn.rollback();
            } catch (SQLException excep) {
                excep.getErrorCode();
            }
        }

        return false;

    }

    /**
     * Method which gets an Context by string.
     *
     * @param contextString
     * @return context
     */
    public Context getContextByString(String contextString) {
        try {
            Connection conn = connectionHandler.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM ContextKeys where context_key = ?");
            pstmt.setString(1, contextString);

            Context context = montarContext(pstmt.executeQuery());

            pstmt.close();
            return context;
        } catch (SQLException e) {
            throw new ContextNaoAssociadoException(contextString + " não está associado a nenhum utilizador");
        }
    }

    /**
     * Boolean method which checks if a Session was inserted.
     *
     * @param session
     * @return boolean
     */
    public boolean insertSession(Session session) {
        Connection conn = connectionHandler.getConnection();

        try {
            PreparedStatement pstmtIdUtilizador = conn.prepareCall("Select idUtilizador from Utilizador where email = ?");
            pstmtIdUtilizador.setString(1, session.getUser().getEmail().toString());
            ResultSet rSetIdUtilizador = pstmtIdUtilizador.executeQuery();
            rSetIdUtilizador.next();

            int idUtilizador = rSetIdUtilizador.getInt(1);

            PreparedStatement pstmtInsertSession = conn.prepareCall("insert into Sessao(IDUTILIZADOR, CONTEXT_KEY, DATA_LOGIN) values (?, ?, ?)");

            conn.setAutoCommit(false);

            pstmtInsertSession.setInt(1, idUtilizador);
            pstmtInsertSession.setString(2, session.getContext().getContext());
            pstmtInsertSession.setDate(3, session.getDate().getDataSQL());

            pstmtInsertSession.executeQuery();

            conn.commit();
            pstmtIdUtilizador.close();
            rSetIdUtilizador.close();
            pstmtInsertSession.close();

            return true;
        } catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();
            try {
                System.err.print("Transaction is being rolled back");
                conn.rollback();
            } catch (SQLException excep) {
                excep.getErrorCode();
            }
        }

        return false;
    }

    /**
     * Boolean method which checks if a context is invalid.
     *
     * @param context
     * @return boolean
     */
    public boolean makeContextInvalid(String context) {
        Connection conn = connectionHandler.getConnection();

        try {

            conn.setAutoCommit(false);
            CallableStatement csUpdateContext = conn.prepareCall("Update CONTEXTKEYS set estado = 'INV'");

            csUpdateContext.executeQuery();

            conn.commit();
            csUpdateContext.close();
            return true;
        } catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();
            try {
                System.err.print("Transaction is being rolled back");
                conn.rollback();
            } catch (SQLException excep) {
                excep.getErrorCode();
            }
        }

        return false;
    }



    /**
     * Method which gets a Session by its Context.
     *
     * @param contextString
     * @return session
     */
    public Session getSessionByContext(String contextString) {
        try {
            Connection conn = connectionHandler.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM SESSAO where context_key = ?");
            pstmt.setString(1, contextString);

            Session session = montarSession(pstmt.executeQuery());

            pstmt.close();
            return session;
        } catch (SQLException e) {
            throw new ContextNaoAssociadoException(contextString + " não está associado a nenhuma sessão");
        }
    }

    private Session montarSession(ResultSet row) {
        Session session = null;

        try {
            Connection conn = connectionHandler.getConnection();

            row.next();

            //Construir objeto User
            PreparedStatement pstmtUtilizador = conn.prepareStatement("SELECT * FROM Utilizador WHERE idUtilizador = ?");
            pstmtUtilizador.setString(1, row.getString("idutilizador"));
            ResultSet rSetUser = pstmtUtilizador.executeQuery();

            User user = RepositorioUtilizador.getInstance().montarUtilizador(rSetUser);


            //Construir Context
            Context context = new Context(row.getString("context_key"), true);


            //Construir LoginDate
            java.sql.Date data = row.getDate("DATA_LOGIN");
            String[] dataString = data.toString().split("-");

            Data dataLogin = new Data(Integer.parseInt(dataString[0]), Integer.parseInt(dataString[1])
                    , Integer.parseInt(dataString[2]));

            session = new Session(user, context, dataLogin);
            row.close();
            pstmtUtilizador.close();

        } catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();

        }
        if (session != null) {
            return session;
        } else {
            throw new FetchingProblemException("Problema a montar sessão");
        }
    }

    private Context montarContext(ResultSet row){
        Context context = null;
        try {
            row.next();
            String contextKey = row.getString(1);
            String estado = row.getString(2);
            boolean valido = estado.equalsIgnoreCase("VAL");


            context = new Context(contextKey, valido);

            row.close();
        } catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();

        }
        if (context != null) {
            return context;
        } else {
            throw new FetchingProblemException("Problema a montar o context");
        }
    }


    /**
     * Method for timeout context.
     *
     * @param context
     */
    public void checkTimeout(String context) {
        Connection conn = connectionHandler.getConnection();

        try {
            conn.setAutoCommit(false);
            CallableStatement csUpdateContext = conn.prepareCall("call checkTimeout(?)");
            csUpdateContext.setString(1, context);

            csUpdateContext.executeQuery();

            conn.commit();
            csUpdateContext.close();
        } catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();
            try {
                System.err.print("Transaction is being rolled back");
                conn.rollback();
            } catch (SQLException excep) {
                excep.getErrorCode();
                
            }
        }

    }
}
