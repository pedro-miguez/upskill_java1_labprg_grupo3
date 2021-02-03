package application;

import domain.CategoriaTarefa;
import domain.Plataforma;

import java.util.ArrayList;

public class PlataformaController {

    public ArrayList<CategoriaTarefa> getCategoriasTarefa() {
        return Plataforma.getInstance().getRepoCategoriaTarefa().listarCategoriasTarefa();
    }

    public void resetUserAPI() {
        Plataforma.getInstance().resetUserAPI();
    }
}
