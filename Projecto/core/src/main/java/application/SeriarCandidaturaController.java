package application;

import domain.Anuncio;
import domain.Candidatura;
import persistence.RepositorioCandidatura;

import java.util.ArrayList;

/**
 * Current class is the one responsible to connect the GUI with the methods 
 * responsible for serialize application.
 * 
 * @author Grupo 3
 */
public class SeriarCandidaturaController {

    public boolean isSeriacaoAutomatica (Anuncio anuncio){
        return anuncio.isSeriacaoAutomatica();
    }

    public ArrayList<Candidatura> candidaturasSeriadasPorValor(Anuncio anuncio){
        ArrayList<Candidatura> candidaturas = RepositorioCandidatura.getInstance().get
    }


}
