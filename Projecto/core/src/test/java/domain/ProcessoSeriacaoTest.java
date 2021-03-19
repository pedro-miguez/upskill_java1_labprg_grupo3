
package domain;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class ProcessoSeriacaoTest {

    @Test
    public void testCreateProcessoSeriacaoValido() {

        TipoRegimento tp = new SeriacaoAutomaticaObrigatoria("designacao", "regras");

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

        Anuncio a = new Anuncio(tarefa, tp,
                new Data(2021, 02, 18),
                new Data(2021, 02, 22),
                new Data(2021, 02, 18),
                new Data(2021, 02, 22),
                new Data(2021, 03, 01),
                new Data(2021, 06, 30));

        AreaAtividade areaAtividade1 = new AreaAtividade(new CodigoUnico("PRG-01"), "Programação de Jogos", "Programação de variados jogos em JAVA");

        CompetenciaTecnica comptec = new CompetenciaTecnica(new CodigoUnico("JAV-11"), areaAtividade1,
                "Programação Java", "Programação muito boa em Java", new ArrayList<>());

        ReconhecimentoCT reconhecimento1 = new ReconhecimentoCT(comptec, new GrauProficiencia(1, "Baixo"), new Data(2021, 02, 20));
        ReconhecimentoCT reconhecimento2 = new ReconhecimentoCT(comptec, new GrauProficiencia(1, "Baixo"), new Data(2021, 02, 21));
        ReconhecimentoCT reconhecimento3 = new ReconhecimentoCT(comptec, new GrauProficiencia(1, "Baixo"), new Data(2021, 02, 22));

        ArrayList<ReconhecimentoCT> reconhecimento = new ArrayList<>();
        reconhecimento.add(reconhecimento1);
        reconhecimento.add(reconhecimento2);
        reconhecimento.add(reconhecimento3);

        HabilitacaoAcademica habilitacoes1 = new HabilitacaoAcademica("Mestre1", "Contabilidade", "ISCAP", 16);
        HabilitacaoAcademica habilitacoes2 = new HabilitacaoAcademica("Mestre2", "Contabilidade", "ISCAP", 18);
        HabilitacaoAcademica habilitacoes3 = new HabilitacaoAcademica("Mestre3", "Contabilidade", "ISCAP", 17);

        ArrayList<HabilitacaoAcademica> habilitacoes = new ArrayList<>();
        habilitacoes.add(habilitacoes1);
        habilitacoes.add(habilitacoes2);
        habilitacoes.add(habilitacoes3);

        Freelancer freelance1 = new Freelancer("Julio", new Telefone(911082647), new Email("julio1@gmail.com"), new NIF(224050693), reconhecimento, habilitacoes);
        Freelancer freelance2 = new Freelancer("Andre", new Telefone(911081647), new Email("julio2@gmail.com"), new NIF(324050608), reconhecimento, habilitacoes);
        Freelancer freelance3 = new Freelancer("Marco", new Telefone(911084647), new Email("julio3@gmail.com"), new NIF(424050608), reconhecimento, habilitacoes);

        Candidatura b = new Candidatura(a, freelance1, new Data(2021, 02, 22), 50, 30, "txtApresentacao", "txtMotivacao");
        Candidatura c = new Candidatura(a, freelance2, new Data(2021, 02, 22), 100, 20, "txtApresentacao", "txtMotivacao");
        Candidatura d = new Candidatura(a, freelance3, new Data(2021, 02, 22), 150, 10, "txtApresentacao", "txtMotivacao");

        Classificacao classificacao1 = new Classificacao(b, 1);
        Classificacao classificacao2 = new Classificacao(c, 2);
        Classificacao classificacao3 = new Classificacao(d, 3);

        ArrayList<Classificacao> classificacoes = new ArrayList<>();
        classificacoes.add(classificacao1);
        classificacoes.add(classificacao2);
        classificacoes.add(classificacao3);

        Colaborador colaboradores1 = new Colaborador("nome", new Telefone(999999998), new Email("colab1@org.com"), "pedreiro1");
        Colaborador colaboradores2 = new Colaborador("nome1", new Telefone(999999997), new Email("colab2@org.com"), "pedreiro2");
        Colaborador colaboradores3 = new Colaborador("nome2", new Telefone(999999996), new Email("colab3@org.com"), "pedreiro3");

        ArrayList<Colaborador> colaboradores = new ArrayList<>();
        colaboradores.add(colaboradores1);
        colaboradores.add(colaboradores2);
        colaboradores.add(colaboradores3);

        ProcessoSeriacao processoSeriacao = new ProcessoSeriacao(a, classificacoes, colaboradores);

        int expected = 3;

        int result = classificacoes.size();

        assertEquals(expected, result);

    }
        @Test(expected = IllegalArgumentException.class)
        public void testCreateProcessoSeriacaoInvalido1 () {

            TipoRegimento tp = new SeriacaoAutomaticaObrigatoria("designacao", "regras");

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

            Anuncio a = new Anuncio(tarefa, tp,
                    new Data(2021, 02, 18),
                    new Data(2021, 02, 22),
                    new Data(2021, 02, 18),
                    new Data(2021, 02, 22),
                    new Data(2021, 03, 01),
                    new Data(2021, 06, 30));

            AreaAtividade areaAtividade1 = new AreaAtividade(new CodigoUnico("PRG-01"), "Programação de Jogos", "Programação de variados jogos em JAVA");

            CompetenciaTecnica comptec = new CompetenciaTecnica(new CodigoUnico("JAV-11"), areaAtividade1,
                    "Programação Java", "Programação muito boa em Java", new ArrayList<>());

            ReconhecimentoCT reconhecimento1 = new ReconhecimentoCT(comptec, new GrauProficiencia(1, "Baixo"), new Data(2021, 02, 20));
            ReconhecimentoCT reconhecimento2 = new ReconhecimentoCT(comptec, new GrauProficiencia(1, "Baixo"), new Data(2021, 02, 21));
            ReconhecimentoCT reconhecimento3 = new ReconhecimentoCT(comptec, new GrauProficiencia(1, "Baixo"), new Data(2021, 02, 22));

            ArrayList<ReconhecimentoCT> reconhecimento = new ArrayList<>();
            reconhecimento.add(reconhecimento1);
            reconhecimento.add(reconhecimento2);
            reconhecimento.add(reconhecimento3);

            HabilitacaoAcademica habilitacoes1 = new HabilitacaoAcademica("Mestre1", "Contabilidade", "ISCAP", 16);
            HabilitacaoAcademica habilitacoes2 = new HabilitacaoAcademica("Mestre2", "Contabilidade", "ISCAP", 18);
            HabilitacaoAcademica habilitacoes3 = new HabilitacaoAcademica("Mestre3", "Contabilidade", "ISCAP", 17);

            ArrayList<HabilitacaoAcademica> habilitacoes = new ArrayList<>();
            habilitacoes.add(habilitacoes1);
            habilitacoes.add(habilitacoes2);
            habilitacoes.add(habilitacoes3);

            Freelancer freelance1 = new Freelancer("Julio", new Telefone(911082647), new Email("julio1@gmail.com"), new NIF(224050693), reconhecimento, habilitacoes);
            Freelancer freelance2 = new Freelancer("Andre", new Telefone(911081647), new Email("julio2@gmail.com"), new NIF(324050608), reconhecimento, habilitacoes);
            Freelancer freelance3 = new Freelancer("Marco", new Telefone(911084647), new Email("julio3@gmail.com"), new NIF(424050608), reconhecimento, habilitacoes);

            Candidatura b = new Candidatura(a, freelance1, new Data(2021, 02, 22), 50, 30, "txtApresentacao", "txtMotivacao");
            Candidatura c = new Candidatura(a, freelance2, new Data(2021, 02, 22), 100, 20, "txtApresentacao", "txtMotivacao");
            Candidatura d = new Candidatura(a, freelance3, new Data(2021, 02, 22), 150, 10, "txtApresentacao", "txtMotivacao");

            Classificacao classificacao1 = new Classificacao(b, 1);
            Classificacao classificacao2 = new Classificacao(c, 2);
            Classificacao classificacao3 = new Classificacao(d, 3);

            ArrayList<Classificacao> classificacoes = new ArrayList<>();
            classificacoes.add(classificacao1);
            classificacoes.add(classificacao2);
            classificacoes.add(classificacao3);

            Colaborador colaboradores1 = new Colaborador("nome", new Telefone(999999998), new Email("colab1@org.com"), "pedreiro1");
            Colaborador colaboradores2 = new Colaborador("nome1", new Telefone(999999997), new Email("colab2@org.com"), "pedreiro2");
            Colaborador colaboradores3 = new Colaborador("nome2", new Telefone(999999996), new Email("colab3@org.com"), "pedreiro3");

            ArrayList<Colaborador> colaboradores = new ArrayList<>();
            colaboradores.add(colaboradores1);
            colaboradores.add(colaboradores2);
            colaboradores.add(colaboradores3);

            ProcessoSeriacao processoSeriacao = new ProcessoSeriacao(a, classificacoes, colaboradores);

            int expected = 2;

            int result = classificacoes.size();

            assertEquals(expected, result);
        }

}