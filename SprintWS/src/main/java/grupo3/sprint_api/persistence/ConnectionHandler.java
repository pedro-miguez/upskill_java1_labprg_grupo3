package grupo3.sprint_api.persistence;

import oracle.jdbc.pool.OracleDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Current class implements the tools to create new connections (as ConnectionHandler).
 */
public class ConnectionHandler {

    private Connection connection;

    public ConnectionHandler() throws SQLException {
        this.connection = openConnection();
    }


    private Connection openConnection() throws SQLException {
        OracleDataSource ods = new OracleDataSource();
        String url = "jdbc:oracle:thin:@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";
        ods.setURL(url);
        ods.setUser("UPSKILL_BD_TURMA1_14");
        ods.setPassword("qwerty");
        return  ods.getConnection();
    }


    /**
     * Method which closes an connection.
     *
     * @throws SQLException
     */
    public void closeConnection() throws SQLException {
        this.connection.close();
    }

    /**
     * Method which gets an connection.
     *
     * @return connection
     */
    public Connection getConnection() {
        return connection;
    }



}
