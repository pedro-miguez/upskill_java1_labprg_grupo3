package persistence;

import domain.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * Class responsible for creating a repository to store information about
 * assignment process.
 * 
 * @author Grupo 3
 */
public class RepositorioAtribuicao {

    private static RepositorioAtribuicao instance;
    
    /**
     * Static method that returns a unique reference to the class object.
     * 
     * @return instance
     */
    public static RepositorioAtribuicao getInstance() {
        if (instance == null) {
            instance = new RepositorioAtribuicao();
        }
        return instance;
    }

    /**
     * Creates an assignment process.
     * 
     * @return 
     */
    public Atribuicao criarProcessoAtribuicao( ) {
                                                
    }

    
    /**
     * Boolean method that checks if a assignment process exists in the 
     * repository, otherwise it is added to it.
     * 
     * @param processoAtribuicao
     * @return 
     */
    public boolean insertProcessoAtribuicao(Atribuicao processoAtribuicao) {
        
    }
    
}
