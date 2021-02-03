package application;

import domain.*;

import java.util.ArrayList;
import java.util.List;

public class DefinirCategoriaTarefaController {

    public boolean definirCategoriaTarefa(String codigoUnicoAreaAtividade, String descricao,
                                          List<CaracterizacaoCompTec> competenciasTecnicas) {

        CategoriaTarefa categoriaTarefa = new CategoriaTarefa(Plataforma.getInstance().getRepoAreaAtiv().
                getAreaAtividadeByCodUnico(new CodigoUnico(codigoUnicoAreaAtividade)),
                descricao, new ArrayList<>((competenciasTecnicas)));

        return Plataforma.getInstance().getRepoCategoriaTarefa().addCategoriaTarefa(categoriaTarefa);
    }

}
