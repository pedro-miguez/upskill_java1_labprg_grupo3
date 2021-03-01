package domain;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TarefaTest {

    @Test
    public void testCreateTarefaValida(){

        Organizacao org = new Organizacao("org", new NIF(123123123), new Website("www.org.com"), new Telefone(999999999),
                new Email("org@org.com"), new EnderecoPostal("Rua da Povoa 23", "Porto", "4200-432"));

        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"), "Programação de Jogos",
                "Programação de variados jogos em JAVA");

        CategoriaTarefa categoriaTarefa = new CategoriaTarefa(areaAtividade, "Programação", new ArrayList<CaracterizacaoCompTec>());

        Tarefa tarefa = new Tarefa(new CodigoUnico("TAR-01"), "Progamador Jogo",
                "É necessário progamador para um jogo",
                "É necessário programador em java para desenvolvimento de um jogo", 100, 1500.00f, categoriaTarefa, org);

        int expected = 100;

        int result = tarefa.getDuracaoDias();

        assertEquals(expected, result);

    }

    @Test (expected = IllegalArgumentException.class)
    public void testCreateTarefaComCodigoUnicoInvalido(){

        Organizacao organizacao = new Organizacao("org", new NIF(123123123), new Website("www.org.com"), new Telefone(999999999),
                new Email("org@org.com"), new EnderecoPostal("Rua da Povoa 23", "Porto", "4200-432"));

        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"), "Programação de Jogos",
                "Programação de variados jogos em JAVA");

        CategoriaTarefa categoriaTarefa = new CategoriaTarefa(areaAtividade, "Programação", new ArrayList<CaracterizacaoCompTec>());

        Tarefa tarefa = new Tarefa(new CodigoUnico("TARR-01"), "Progamador Jogo",
                "É necessário progamador para um jogo",
                "É necessário programador em java para desenvolvimento de um jogo", 100, 1500.00f, categoriaTarefa, organizacao);

    }

    @Test (expected = IllegalArgumentException.class)
    public void testCreateTarefaComCodigoUnicoInvalido2(){
        Organizacao organizacao = new Organizacao("org", new NIF(123123123), new Website("www.org.com"), new Telefone(999999999),
                new Email("org@org.com"), new EnderecoPostal("Rua da Povoa 23", "Porto", "4200-432"));

        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"), "Programação de Jogos",
                "Programação de variados jogos em JAVA");

        CategoriaTarefa categoriaTarefa = new CategoriaTarefa(areaAtividade, "Programação", new ArrayList<CaracterizacaoCompTec>());

        Tarefa tarefa = new Tarefa(new CodigoUnico("TAR-011"), "Progamador Jogo",
                "É necessário progamador para um jogo",
                "É necessário programador em java para desenvolvimento de um jogo", 100, 1500.00f, categoriaTarefa, organizacao);

    }

    @Test (expected = IllegalArgumentException.class)
    public void testCreateTarefaComDesignacaoInvalida(){

        Organizacao organizacao = new Organizacao("org", new NIF(123123123), new Website("www.org.com"), new Telefone(999999999),
                new Email("org@org.com"), new EnderecoPostal("Rua da Povoa 23", "Porto", "4200-432"));

        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"), "Programação de Jogos",
                "Programação de variados jogos em JAVA");

        CategoriaTarefa categoriaTarefa = new CategoriaTarefa(areaAtividade, "Programação", new ArrayList<CaracterizacaoCompTec>());

        Tarefa tarefa = new Tarefa(new CodigoUnico("TAR-01"), " ",
                "É necessário progamador para um jogo",
                "É necessário programador em java para desenvolvimento de um jogo", 100, 1500.00f, categoriaTarefa, organizacao);

    }

    @Test (expected = IllegalArgumentException.class)
    public void testCreateTarefaComDescricaoInformalInvalida(){

        Organizacao organizacao = new Organizacao("org", new NIF(123123123), new Website("www.org.com"), new Telefone(999999999),
                new Email("org@org.com"), new EnderecoPostal("Rua da Povoa 23", "Porto", "4200-432"));

        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"), "Programação de Jogos",
                "Programação de variados jogos em JAVA");

        CategoriaTarefa categoriaTarefa = new CategoriaTarefa(areaAtividade, "Programação", new ArrayList<CaracterizacaoCompTec>());

        Tarefa tarefa = new Tarefa(new CodigoUnico("TAR-01"), "Progamador Jogo",
                " ",
                "É necessário programador em java para desenvolvimento de um jogo", 100, 1500.00f, categoriaTarefa, organizacao);

    }

    @Test (expected = IllegalArgumentException.class)
    public void testCreateTarefaComDescricaoTecnicaInvalida(){

        Organizacao organizacao = new Organizacao("org", new NIF(123123123), new Website("www.org.com"), new Telefone(999999999),
                new Email("org@org.com"), new EnderecoPostal("Rua da Povoa 23", "Porto", "4200-432"));

        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"), "Programação de Jogos",
                "Programação de variados jogos em JAVA");

        CategoriaTarefa categoriaTarefa = new CategoriaTarefa(areaAtividade, "Programação", new ArrayList<CaracterizacaoCompTec>());

        Tarefa tarefa = new Tarefa(new CodigoUnico("TAR-01"), "Progamador Jogo",
                "É necessário progamador para um jogo",
                " ", 100, 1500.00f, categoriaTarefa, organizacao);

    }

    @Test (expected = IllegalArgumentException.class)
    public void testCreateTarefaComDuracaoInvalida(){

        Organizacao organizacao = new Organizacao("org", new NIF(123123123), new Website("www.org.com"), new Telefone(999999999),
                new Email("org@org.com"), new EnderecoPostal("Rua da Povoa 23", "Porto", "4200-432"));

        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"), "Programação de Jogos",
                "Programação de variados jogos em JAVA");

        CategoriaTarefa categoriaTarefa = new CategoriaTarefa(areaAtividade, "Programação", new ArrayList<CaracterizacaoCompTec>());

        Tarefa tarefa = new Tarefa(new CodigoUnico("TAR-01"), "Progamador Jogo",
                "É necessário progamador para um jogo",
                "É necessário programador em java para desenvolvimento de um jogo", 0, 1500.00f, categoriaTarefa, organizacao);

    }

    @Test (expected = IllegalArgumentException.class)
    public void testCreateTarefaComCustoInvalido(){

        Organizacao organizacao = new Organizacao("org", new NIF(123123123), new Website("www.org.com"), new Telefone(999999999),
                new Email("org@org.com"), new EnderecoPostal("Rua da Povoa 23", "Porto", "4200-432"));

        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"), "Programação de Jogos",
                "Programação de variados jogos em JAVA");

        CategoriaTarefa categoriaTarefa = new CategoriaTarefa(areaAtividade, "Programação", new ArrayList<CaracterizacaoCompTec>());

        Tarefa tarefa = new Tarefa(new CodigoUnico("TAR-01"), "Progamador Jogo",
                "É necessário progamador para um jogo",
                "É necessário programador em java para desenvolvimento de um jogo", 100, 0, categoriaTarefa, organizacao);

    }



}