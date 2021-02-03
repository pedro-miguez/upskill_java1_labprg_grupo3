package persistence;

import domain.*;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class RepositorioTarefaTest {

    @Test
    public void testGetInstance() {

        RepositorioTarefa expected = RepositorioTarefa.getInstance();

        RepositorioTarefa result = RepositorioTarefa.getInstance();

        assertEquals(expected, result);
    }

    @Test
    public void addTarefaValida(){

        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"), "Programação de Jogos",
                "Programação de variados jogos em JAVA");

        CategoriaTarefa categoriaTarefa = new CategoriaTarefa(areaAtividade, "Programação", new ArrayList<CaracterizacaoCompTec>());

        Tarefa tarefa = new Tarefa(new CodigoUnico("TAR-01"), "Progamador Jogo",
                "É necessário progamador para um jogo",
                "É necessário programador em java para desenvolvimento de um jogo", 100, 1500.00f, categoriaTarefa);

        assertTrue(RepositorioTarefa.getInstance().addTarefa(tarefa));

    }

    @Test
    public void addTarefaDuplicada(){

        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"), "Programação de Jogos",
                "Programação de variados jogos em JAVA");

        CategoriaTarefa categoriaTarefa = new CategoriaTarefa(areaAtividade, "Programação", new ArrayList<CaracterizacaoCompTec>());

        Tarefa tarefa = new Tarefa(new CodigoUnico("TAR-01"), "Progamador Jogo",
                "É necessário progamador para um jogo",
                "É necessário programador em java para desenvolvimento de um jogo", 100, 1500.00f, categoriaTarefa);

        Tarefa tarefa1 = new Tarefa(new CodigoUnico("TAR-01"), "Progamador Jogo",
                "É necessário progamador para um jogo",
                "É necessário programador em java para desenvolvimento de um jogo", 100, 1500.00f, categoriaTarefa);

        RepositorioTarefa.getInstance().addTarefa(tarefa);

        assertFalse(RepositorioTarefa.getInstance().addTarefa(tarefa1));

    }

    @Test
    public void getTarefaByCodigoUnicoValido(){

        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"), "Programação de Jogos",
                "Programação de variados jogos em JAVA");

        CategoriaTarefa categoriaTarefa = new CategoriaTarefa(areaAtividade, "Programação", new ArrayList<CaracterizacaoCompTec>());

        CodigoUnico codigoUnico = new CodigoUnico("TAR-01");

        Tarefa tarefa = new Tarefa(codigoUnico, "Progamador Jogo",
                "É necessário progamador para um jogo",
                "É necessário programador em java para desenvolvimento de um jogo", 100, 1500.00f, categoriaTarefa);

        RepositorioTarefa.getInstance().addTarefa(tarefa);

        Tarefa tarefa1 = RepositorioTarefa.getInstance().getTarefaByCodigoUnico(codigoUnico);

        assertEquals(tarefa, tarefa1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void getTarefaByCodigoUnicoInvalido(){

        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"), "Programação de Jogos",
                "Programação de variados jogos em JAVA");

        CategoriaTarefa categoriaTarefa = new CategoriaTarefa(areaAtividade, "Programação", new ArrayList<CaracterizacaoCompTec>());

        CodigoUnico codigoUnico = new CodigoUnico("TAR-01");

        CodigoUnico codigoUnico1 = new CodigoUnico("TAR-02");

        Tarefa tarefa = new Tarefa(codigoUnico, "Progamador Jogo",
                "É necessário progamador para um jogo",
                "É necessário programador em java para desenvolvimento de um jogo", 100, 1500.00f, categoriaTarefa);

        RepositorioTarefa.getInstance().addTarefa(tarefa);

        Tarefa tarefa1 = RepositorioTarefa.getInstance().getTarefaByCodigoUnico(codigoUnico1);

    }

}