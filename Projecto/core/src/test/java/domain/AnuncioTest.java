/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 *
 * @author Grupo 3
 */
public class AnuncioTest {

    @Test
    public void testCreateAnuncioValido() {

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

        Anuncio a = new Anuncio (tarefa, new TipoRegimento("Regimento a todo o gas",
                "as regras existem para ser quebradas"),
                                    new Data(2021, 02, 18),
                                    new Data(2021, 02, 22),
                                    new Data(2021, 02, 18),
                                    new Data(2021, 02, 22),
                                    new Data(2021, 03, 01),
                                    new Data(2021, 06, 30));
        
        Data expected = new Data(2021, 02, 18);
        
        Data result = a.getDataInicioPublicitacao();
        
        assertEquals(expected, result);
        
    }
    
    
    @Test (expected = IllegalArgumentException.class)
    public void testCreateAnuncioComDataInvalida() {

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

        Anuncio a = new Anuncio (tarefa, new TipoRegimento("Regimento a todo o gas",
                "as regras existem para ser quebradas"),
                new Data(2021, 02, 18),
                new Data(2021, 02, 17),
                new Data(2021, 02, 18),
                new Data(2021, 02, 22),
                new Data(2021, 03, 01),
                new Data(2021, 06, 30));
}

    @Test (expected = IllegalArgumentException.class)
    public void testCreateAnuncioComDataInvalida2() {

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

    Anuncio a = new Anuncio (tarefa, new TipoRegimento("Regimento a todo o gas",
            "as regras existem para ser quebradas"),
            new Data(2021, 02, 18),
            new Data(2021, 02, 17),
            new Data(2021, 02, 18),
            new Data(2021, 02, 22),
            new Data(2021, 03, 01),
            new Data(2020, 06, 30));
}
    @Test (expected = IllegalArgumentException.class)
    public void testCreateAnuncioComDataInvalida3() {

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

    Anuncio a = new Anuncio (tarefa, new TipoRegimento("Regimento a todo o gas",
            "as regras existem para ser quebradas"),
            new Data(2021, 02, 18),
            new Data(2021, 02, 17),
            new Data(2021, 02, 18),
            new Data(2021, 02, 22),
            new Data(2021, 03, 01),
            new Data(2020, 06, 30));
}
    @Test (expected = IllegalArgumentException.class)
    public void testCreateAnuncioComTipoDeRegimentoInvalido() {

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

        Anuncio a = new Anuncio (tarefa, new TipoRegimento("",
                "as regras existem para ser quebradas"),
                new Data(2021, 02, 18),
                new Data(2021, 02, 17),
                new Data(2021, 02, 18),
                new Data(2021, 02, 22),
                new Data(2021, 03, 01),
                new Data(2020, 06, 30));
    }
    @Test (expected = IllegalArgumentException.class)
    public void testCreateAnuncioComTipoDeRegimentoInvalido2() {

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

        Anuncio a = new Anuncio (tarefa, new TipoRegimento("Regimento a todo o gas",
                ""),
                new Data(2021, 02, 18),
                new Data(2021, 02, 17),
                new Data(2021, 02, 18),
                new Data(2021, 02, 22),
                new Data(2021, 03, 01),
                new Data(2020, 06, 30));
    }

}
