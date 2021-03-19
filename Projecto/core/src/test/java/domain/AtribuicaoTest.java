package domain;

import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AtribuicaoTest {

    @Test
    public void testCreateAtribuicaoValida() {

        List<CaracterizacaoCompTec> competenciasTecnicas = new ArrayList<>();

        CodigoUnico codigoUnico = new CodigoUnico("PRG-01");

        AreaAtividade areaAtividade = new AreaAtividade(codigoUnico, "Programação de Jogos",
                "Programação de variados jogos em JAVA");

        CategoriaTarefa ct = new CategoriaTarefa(areaAtividade, "Programacao", competenciasTecnicas);

        Organizacao org = new Organizacao("org", new NIF(123123123), new Website("www.org.com"), new Telefone(999999999),
                new Email("org@org.com"), new EnderecoPostal("Rua da Povoa 23", "Porto", "4200-432"));

        Tarefa tarefa = new Tarefa(new CodigoUnico("TAR-01"), "Progamador Jogo",
                "É necessário progamador para um jogo",
                "É necessário programador em java para desenvolvimento de um jogo",
                100, 1500.00f, ct, org);

        Anuncio a = new Anuncio(tarefa, new SeriacaoAutomaticaObrigatoria("Regimento a todo o gas",
                "as regras existem para ser quebradas"),
                new Data(2021, 02, 18),
                new Data(2021, 02, 22),
                new Data(2021, 02, 18),
                new Data(2021, 02, 22),
                new Data(2021, 03, 01),
                new Data(2021, 06, 30));

        Freelancer freelancer = new Freelancer("Bob Marley", new Telefone(999999999), new Email("colab@org.com"),
                new NIF(214852581), new ArrayList<>(), new ArrayList<>());

        Candidatura cand = new Candidatura(a, freelancer, new Data(2021, 02, 18),
                1200, 90, "Projeto novo", "Novos desafios");

        Classificacao classificacao = new Classificacao(cand, 1);

        Atribuicao atribuicao = new Atribuicao(classificacao, LocalDate.of(2020, 03, 19));

        LocalDate expected = LocalDate.of(2020, 03, 19);

        Data result = atribuicao.getDataInicio();

        assertEquals(expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateAtribuicaoInvalida() {

        List<CaracterizacaoCompTec> competenciasTecnicas = new ArrayList<>();

        CodigoUnico codigoUnico = new CodigoUnico("PRG-01");

        AreaAtividade areaAtividade = new AreaAtividade(codigoUnico, "Programação de Jogos",
                "Programação de variados jogos em JAVA");

        CategoriaTarefa ct = new CategoriaTarefa(areaAtividade, "Programacao", competenciasTecnicas);

        Organizacao org = new Organizacao("org", new NIF(123123123), new Website("www.org.com"), new Telefone(999999999),
                new Email("org@org.com"), new EnderecoPostal("Rua da Povoa 23", "Porto", "4200-432"));

        Tarefa tarefa = new Tarefa(new CodigoUnico("TAR-01"), "Progamador Jogo",
                "É necessário progamador para um jogo",
                "É necessário programador em java para desenvolvimento de um jogo",
                100, 1500.00f, ct, org);

        Anuncio a = new Anuncio(tarefa, new SeriacaoAutomaticaObrigatoria("Regimento a todo o gas",
                "as regras existem para ser quebradas"),
                new Data(2021, 02, 18),
                new Data(2021, 02, 22),
                new Data(2021, 02, 18),
                new Data(2021, 02, 22),
                new Data(2021, 03, 01),
                new Data(2021, 06, 30));

        Freelancer freelancer = new Freelancer("Bob Marley", new Telefone(999999999), new Email("colab@org.com"),
                new NIF(214852581), new ArrayList<>(), new ArrayList<>());

        Candidatura cand = new Candidatura(a, freelancer, new Data(2021, 02, 18),
                1200, 90, "Projeto novo", "Novos desafios");

        Classificacao classificacao = new Classificacao(cand, 1);

        Atribuicao atribuicao = new Atribuicao(classificacao, LocalDate.of(2020, 03, 40));
    }
}