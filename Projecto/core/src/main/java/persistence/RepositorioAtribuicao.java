package persistence;

import domain.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class RepositorioAtribuicao {

    private static RepositorioAtribuicao instance;
    
    public static RepositorioAtribuicao getInstance() {
        if (instance == null) {
            instance = new RepositorioAtribuicao();
        }
        return instance;
    }

    public Atribuicao criarProcessoAtribuicao( ) {
                                                
    }

    public boolean insertProcessoAtribuicao(Atribuicao processoAtribuicao) {
        
    }
    
}
