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

        Tarefa tarefa = new Tarefa("TAR-01", "Progamador Jogo",
                "É necessário progamador para um jogo",
                "É necessário programador em java para desenvolvimento de um jogo", 100, 1500.00f, categoriaTarefa);

        assertTrue(RepositorioTarefa.getInstance().addTarefa(tarefa));

    }

    @Test
    public void addTarefaDuplicada(){

        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"), "Programação de Jogos",
                "Programação de variados jogos em JAVA");

        CategoriaTarefa categoriaTarefa = new CategoriaTarefa(areaAtividade, "Programação", new ArrayList<CaracterizacaoCompTec>());

        Tarefa tarefa = new Tarefa("TAR-01", "Progamador Jogo",
                "É necessário progamador para um jogo",
                "É necessário programador em java para desenvolvimento de um jogo", 100, 1500.00f, categoriaTarefa);

        Tarefa tarefa1 = new Tarefa("TAR-01", "Progamador Jogo",
                "É necessário progamador para um jogo",
                "É necessário programador em java para desenvolvimento de um jogo", 100, 1500.00f, categoriaTarefa);

        RepositorioTarefa.getInstance().addTarefa(tarefa);

        assertFalse(RepositorioTarefa.getInstance().addTarefa(tarefa1));

    }

    @Test
    public void getTarefaByReferenciaValida(){

        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"), "Programação de Jogos",
                "Programação de variados jogos em JAVA");

        CategoriaTarefa categoriaTarefa = new CategoriaTarefa(areaAtividade, "Programação", new ArrayList<CaracterizacaoCompTec>());

        Tarefa tarefa = new Tarefa("TAR-01", "Progamador Jogo",
                "É necessário progamador para um jogo",
                "É necessário programador em java para desenvolvimento de um jogo", 100, 1500.00f, categoriaTarefa);

        RepositorioTarefa.getInstance().addTarefa(tarefa);

        Tarefa tarefa1 = RepositorioTarefa.getInstance().getTarefaByReferencia("TAR-01");

        assertEquals(tarefa, tarefa1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void getTarefaByReferenciaInvalida(){

        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"), "Programação de Jogos",
                "Programação de variados jogos em JAVA");

        CategoriaTarefa categoriaTarefa = new CategoriaTarefa(areaAtividade, "Programação", new ArrayList<CaracterizacaoCompTec>());

        Tarefa tarefa = new Tarefa("TAR-01", "Progamador Jogo",
                "É necessário progamador para um jogo",
                "É necessário programador em java para desenvolvimento de um jogo", 100, 1500.00f, categoriaTarefa);

        RepositorioTarefa.getInstance().addTarefa(tarefa);

        Tarefa tarefa1 = RepositorioTarefa.getInstance().getTarefaByReferencia("TAR-02");

    }

}