package persistence;

import domain.*;
import exceptions.CodigoNaoAssociadoException;
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
        Organizacao organizacao = new Organizacao("org", new NIF(123123123), new Website("www.org.com"), new Telefone(999999999),
                new Email("org@org.com"), new EnderecoPostal("Rua da Povoa 23", "Porto", "4200-432"));

        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"), "Programação de Jogos",
                "Programação de variados jogos em JAVA");

        CategoriaTarefa categoriaTarefa = new CategoriaTarefa(areaAtividade, "Programação", new ArrayList<CaracterizacaoCompTec>());

        Tarefa tarefa = new Tarefa(new CodigoUnico("TAR-02"), "Design Jogo",
                "É necessário progamador para um jogo",
                "É necessário programador em java para desenvolvimento de um jogo", 100, 1500.00f, categoriaTarefa, organizacao);

        assertTrue(RepositorioTarefa.getInstance().addTarefa(tarefa));

    }

    @Test
    public void addTarefaDuplicada(){
        Organizacao organizacao = new Organizacao("org", new NIF(123123123), new Website("www.org.com"), new Telefone(999999999),
                new Email("org@org.com"), new EnderecoPostal("Rua da Povoa 23", "Porto", "4200-432"));

        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"), "Programação de Jogos",
                "Programação de variados jogos em JAVA");

        CategoriaTarefa categoriaTarefa = new CategoriaTarefa(areaAtividade, "Programação", new ArrayList<CaracterizacaoCompTec>());

        Tarefa tarefa = new Tarefa(new CodigoUnico("TAR-01"), "Progamador Jogo",
                "É necessário progamador para um jogo",
                "É necessário programador em java para desenvolvimento de um jogo", 100, 1500.00f, categoriaTarefa, organizacao);

        Tarefa tarefa1 = new Tarefa(new CodigoUnico("TAR-01"), "Progamador Jogo",
                "É necessário progamador para um jogo",
                "É necessário programador em java para desenvolvimento de um jogo", 100, 1500.00f, categoriaTarefa, organizacao);

        RepositorioTarefa.getInstance().addTarefa(tarefa);

        assertFalse(RepositorioTarefa.getInstance().addTarefa(tarefa1));

    }

    @Test
    public void getTarefaByCodigoUnicoValido(){
        Organizacao organizacao = new Organizacao("org", new NIF(123123123), new Website("www.org.com"), new Telefone(999999999),
                new Email("org@org.com"), new EnderecoPostal("Rua da Povoa 23", "Porto", "4200-432"));

        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"), "Programação de Jogos",
                "Programação de variados jogos em JAVA");

        CategoriaTarefa categoriaTarefa = new CategoriaTarefa(areaAtividade, "Programação", new ArrayList<CaracterizacaoCompTec>());

        CodigoUnico codigoUnico = new CodigoUnico("TAR-01");

        Tarefa tarefa = new Tarefa(codigoUnico, "Progamador Jogo",
                "É necessário progamador para um jogo",
                "É necessário programador em java para desenvolvimento de um jogo", 100, 1500.00f, categoriaTarefa, organizacao);

        RepositorioTarefa.getInstance().addTarefa(tarefa);

        Tarefa tarefa1 = RepositorioTarefa.getInstance().getTarefaByCodigoUnico(codigoUnico);

        assertEquals(tarefa, tarefa1);
    }

    @Test (expected = CodigoNaoAssociadoException.class)
    public void getTarefaByCodigoUnicoInvalido(){
        RepositorioTarefa.getInstance().getTarefaByCodigoUnico(new CodigoUnico("ZZZ-99"));

    }

}