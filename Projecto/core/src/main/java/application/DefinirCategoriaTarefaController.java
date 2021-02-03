package application;

import domain.*;

import java.util.ArrayList;
import java.util.List;

public class DefinirCategoriaTarefaController {

    public boolean definirCategoriaTarefa(AreaAtividade areaAtividade, String descricao,
                                          List<CaracterizacaoCompTec> competenciasTecnicas) {

        CategoriaTarefa categoriaTarefa = new CategoriaTarefa(areaAtividade,
                descricao, new ArrayList<>(competenciasTecnicas));

        return Plataforma.getInstance().getRepoCategoriaTarefa().addCategoriaTarefa(categoriaTarefa);
    }

}
