package network;

import oracle.jdbc.pool.OracleDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Class responsible for creating a connection.
 * 
 * @author Grupo 3
 */
public class ConnectionHandler {

    private Connection connection;

    public ConnectionHandler() throws SQLException {
        this.connection = openConnection();
    }


    /**
     * Opens a connection.
     * 
     * @return ods
     * @throws SQLException 
     */
    private Connection openConnection() throws SQLException {
        OracleDataSource ods = new OracleDataSource();
        String url = "jdbc:oracle:thin:@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";
        ods.setURL(url);
        ods.setUser("UPSKILL_BD_TURMA1_14");
        ods.setPassword("qwerty");
        return  ods.getConnection();
    }



    /**
     * Closes the connection.
     * 
     * @throws SQLException 
     */
    public void closeConnection() throws SQLException {
        this.connection.close();
    }

    /**
     * Gets an connection.
     * 
     * @return connection
     */
    public Connection getConnection() {
        return connection;
    }



}
