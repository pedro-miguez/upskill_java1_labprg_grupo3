package domain;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TarefaTest {

    @Test
    public void testCreateTarefaValida(){


        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"), "Programação de Jogos",
                "Programação de variados jogos em JAVA");

/*        CompetenciaTecnica ct = new CompetenciaTecnica(new CodigoUnico("JAV-01"), areaAtividade,
                "Programação Java", "Programação muito boa em Java");

        CaracterizacaoCompTec compTec = new CaracterizacaoCompTec(ct, true, GrauProficiencia.INTERMEDIO);*/

        CategoriaTarefa categoriaTarefa = new CategoriaTarefa(areaAtividade, "Programação", new ArrayList<CaracterizacaoCompTec>());

        Tarefa tarefa = new Tarefa(new CodigoUnico("TAR-01"), "Progamador Jogo",
                "É necessário progamador para um jogo",
                "É necessário programador em java para desenvolvimento de um jogo", 100, 1500.00f, categoriaTarefa);

        String result = tarefa.toString();

        String expectedResult = "Código Único: TAR-01; Designacao: Progamador Jogo; Descrição Informal: É necessário progamador para um jogo; " +
                "Descrição Técnica: É necessário programador em java para desenvolvimento de um jogo; " + "Estivativa de Duração: 100 horas; " +
                "Estimativa de Custo: 1500,00 €; Categoria Tarefa: Programação.";

        assertEquals(expectedResult, result);

    }

    @Test (expected = IllegalArgumentException.class)
    public void testCreateTarefaComCodigoUnicoInvalido(){

        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"), "Programação de Jogos",
                "Programação de variados jogos em JAVA");

        CategoriaTarefa categoriaTarefa = new CategoriaTarefa(areaAtividade, "Programação", new ArrayList<CaracterizacaoCompTec>());

        Tarefa tarefa = new Tarefa(new CodigoUnico("TARR-01"), "Progamador Jogo",
                "É necessário progamador para um jogo",
                "É necessário programador em java para desenvolvimento de um jogo", 100, 1500.00f, categoriaTarefa);

    }

    @Test (expected = IllegalArgumentException.class)
    public void testCreateTarefaComCodigoUnicoInvalido2(){

        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"), "Programação de Jogos",
                "Programação de variados jogos em JAVA");

        CategoriaTarefa categoriaTarefa = new CategoriaTarefa(areaAtividade, "Programação", new ArrayList<CaracterizacaoCompTec>());

        Tarefa tarefa = new Tarefa(new CodigoUnico("TAR-011"), "Progamador Jogo",
                "É necessário progamador para um jogo",
                "É necessário programador em java para desenvolvimento de um jogo", 100, 1500.00f, categoriaTarefa);

    }

    @Test (expected = IllegalArgumentException.class)
    public void testCreateTarefaComDesignacaoInvalida(){

        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"), "Programação de Jogos",
                "Programação de variados jogos em JAVA");

        CategoriaTarefa categoriaTarefa = new CategoriaTarefa(areaAtividade, "Programação", new ArrayList<CaracterizacaoCompTec>());

        Tarefa tarefa = new Tarefa(new CodigoUnico("TAR-01"), " ",
                "É necessário progamador para um jogo",
                "É necessário programador em java para desenvolvimento de um jogo", 100, 1500.00f, categoriaTarefa);

    }

    @Test (expected = IllegalArgumentException.class)
    public void testCreateTarefaComDescricaoInformalInvalida(){

        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"), "Programação de Jogos",
                "Programação de variados jogos em JAVA");

        CategoriaTarefa categoriaTarefa = new CategoriaTarefa(areaAtividade, "Programação", new ArrayList<CaracterizacaoCompTec>());

        Tarefa tarefa = new Tarefa(new CodigoUnico("TAR-01"), "Progamador Jogo",
                " ",
                "É necessário programador em java para desenvolvimento de um jogo", 100, 1500.00f, categoriaTarefa);

    }

    @Test (expected = IllegalArgumentException.class)
    public void testCreateTarefaComDescricaoTecnicaInvalida(){

        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"), "Programação de Jogos",
                "Programação de variados jogos em JAVA");

        CategoriaTarefa categoriaTarefa = new CategoriaTarefa(areaAtividade, "Programação", new ArrayList<CaracterizacaoCompTec>());

        Tarefa tarefa = new Tarefa(new CodigoUnico("TAR-01"), "Progamador Jogo",
                "É necessário progamador para um jogo",
                " ", 100, 1500.00f, categoriaTarefa);

    }

    @Test (expected = IllegalArgumentException.class)
    public void testCreateTarefaComDuracaoInvalida(){

        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"), "Programação de Jogos",
                "Programação de variados jogos em JAVA");

        CategoriaTarefa categoriaTarefa = new CategoriaTarefa(areaAtividade, "Programação", new ArrayList<CaracterizacaoCompTec>());

        Tarefa tarefa = new Tarefa(new CodigoUnico("TAR-01"), "Progamador Jogo",
                "É necessário progamador para um jogo",
                "É necessário programador em java para desenvolvimento de um jogo", 0, 1500.00f, categoriaTarefa);

    }

    @Test (expected = IllegalArgumentException.class)
    public void testCreateTarefaComCustoInvalido(){

        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"), "Programação de Jogos",
                "Programação de variados jogos em JAVA");

        CategoriaTarefa categoriaTarefa = new CategoriaTarefa(areaAtividade, "Programação", new ArrayList<CaracterizacaoCompTec>());

        Tarefa tarefa = new Tarefa(new CodigoUnico("TAR-01"), "Progamador Jogo",
                "É necessário progamador para um jogo",
                "É necessário programador em java para desenvolvimento de um jogo", 100, 0, categoriaTarefa);

    }



}