package domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class TarefaTest {

    @Test
    public void testCreateTarefaValida(){

        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"), "Programação de Jogos", "Programação de variados jogos em JAVA");

        CompetenciaTecnica ct = new CompetenciaTecnica(new CodigoUnico("JAV-01"), areaAtividade,
                "Programação Java", "Programação muito boa em Java");

        CategoriaTarefa categoriaTarefa = new CategoriaTarefa();

        Tarefa tarefa = new Tarefa("TAR-01", "Progamador Jogo",
                "É necessário progamador para um jogo",
                "É necessário programador em java para desenvolvimetno de um jogo", 100, 1500.00,);

    }

    @Test
    public void testCreateTarefaComReferenciaInvalida(){

    }

    @Test
    public void testCreateTarefaComDesignacaoInvalida(){

    }

    @Test
    public void testCreateTarefaComDescricaoInformalInvalida(){

    }

    @Test
    public void testCreateTarefaComDescricaoTecnicaInvalida(){

    }

    @Test
    public void testCreateTarefaComDuracaoInvalida(){

    }

    @Test
    public void testCreateTarefaComCustoInvalido(){

    }



}