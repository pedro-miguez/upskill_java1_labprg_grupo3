package application;

import domain.Anuncio;
import domain.Candidatura;
import persistence.RepositorioCandidatura;

import java.util.ArrayList;

public class SeriarCandidaturaController {

    public boolean isSeriacaoAutomatica (Anuncio anuncio){
        return anuncio.isSeriacaoAutomatica();
    }

    public ArrayList<Candidatura> candidaturasSeriadasPorValor(Anuncio anuncio){
        ArrayList<Candidatura> candidaturas = RepositorioCandidatura.getInstance().get
    }


}
