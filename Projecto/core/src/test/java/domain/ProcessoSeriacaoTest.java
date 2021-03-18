/*


package domain;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class ProcessoSeriacaoTest {

    @Test
    public void testCreateProcessoSeriacaoValido() {


        */
/*private Anuncio anuncio;
        private Data data;
        private List<Classificacao> classificacao;
        private List<Colaborador> colaboradores;*//*


        Anuncio a = new Anuncio(tarefa, tp,
                new Data(2021, 02, 18),
                new Data(2021, 02, 22),
                new Data(2021, 02, 18),
                new Data(2021, 02, 22),
                new Data(2021, 03, 01),
                new Data(2021, 06, 30));

        Classificacao classificacao1 = new Classificacao(a, freelance1, 1);
        Classificacao classificacao2 = new Classificacao(a, freelance2, 2);
        Classificacao classificacao3 = new Classificacao(a, freelance3, 3);

        ArrayList<Classificacao> classificacoes = new ArrayList<>();
        classificacoes.add(classificacao1);
        classificacoes.add(classificacao2);
        classificacoes.add(classificacao3);

        Colaborador colaboradores1 = new Colaborador();
        Colaborador colaboradores2 = new Colaborador();
        Colaborador colaboradores3 = new Colaborador();

        ArrayList<Colaborador> colaboradores = new ArrayList<>();
        colaboradores.add(colaboradores1);
        colaboradores.add(colaboradores2);
        colaboradores.add(colaboradores3);













        List<CaracterizacaoCompTec> competenciasTecnicas = new ArrayList<>();
        List<Colaborador> colaboradores = new ArrayList<>();
        List<ReconhecimentoCT> reconhcimento = new ArrayList<>();
        List<HabilitacaoAcademica> habilitacoes = new ArrayList<>();

        Freelancer freelance1 = new Freelancer("Julio", new Telefone(911082647), new Email("julio1@gmail.com"), new NIF(224050693), reconhcimento, habilitacoes);
        Freelancer freelance2 = new Freelancer("Andre", new Telefone(911081647), new Email("julio2@gmail.com"), new NIF(324050608), reconhcimento, habilitacoes);
        Freelancer freelance3 = new Freelancer("Marco", new Telefone(911084647), new Email("julio3@gmail.com"), new NIF(424050608), reconhcimento, habilitacoes);

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

        TipoRegimento tp = new SeriacaoAutomaticaObrigatoria("designacao", "regras");

        Anuncio a = new Anuncio(tarefa, tp,
                new Data(2021, 02, 18),
                new Data(2021, 02, 22),
                new Data(2021, 02, 18),
                new Data(2021, 02, 22),
                new Data(2021, 03, 01),
                new Data(2021, 06, 30));

        Classificacao classificacao1 = new Classificacao(a, freelance1, 1);
        Classificacao classificacao2 = new Classificacao(a, freelance2, 2);
        Classificacao classificacao3 = new Classificacao(a, freelance3, 3);

        ArrayList<Classificacao> classificacoes = new ArrayList<>();
        classificacoes.add(classificacao1);
        classificacoes.add(classificacao2);
        classificacoes.add(classificacao3);


        ProcessoSeriacao processoSeriacao = new ProcessoSeriacao(a, classificacoes, colaboradores);

        int expected = 3;

        int result = classificacoes.size();

        assertEquals(expected, result);
    }
        @Test(expected = IllegalArgumentException.class)
        public void testCreateProcessoSeriacaoInvalido1 () {

            List<CaracterizacaoCompTec> competenciasTecnicas = new ArrayList<>();
            List<Colaborador> colaboradores = new ArrayList<>();
            List<ReconhecimentoCT> reconhcimento = new ArrayList<>();
            List<HabilitacaoAcademica> habilitacoes = new ArrayList<>();

            Freelancer freelance1 = new Freelancer("Julio", new Telefone(911082647), new Email("julio1@gmail.com"), new NIF(224050693), reconhcimento, habilitacoes);
            Freelancer freelance2 = new Freelancer("Andre", new Telefone(911081647), new Email("julio2@gmail.com"), new NIF(324050608), reconhcimento, habilitacoes);
            Freelancer freelance3 = new Freelancer("Marco", new Telefone(911084647), new Email("julio3@gmail.com"), new NIF(424050608), reconhcimento, habilitacoes);

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

            TipoRegimento tp = new SeriacaoAutomaticaObrigatoria("designacao", "regras");

            Anuncio a = new Anuncio(tarefa, tp,
                    new Data(2021, 02, 18),
                    new Data(2021, 02, 22),
                    new Data(2021, 02, 18),
                    new Data(2021, 02, 22),
                    new Data(2021, 03, 01),
                    new Data(2021, 06, 30));

            Classificacao classificacao1 = new Classificacao(a, freelance1, 1);
            Classificacao classificacao2 = new Classificacao(a, freelance2, 2);
            Classificacao classificacao3 = new Classificacao(a, freelance3, 3);

            ArrayList<Classificacao> classificacoes = new ArrayList<>();
            classificacoes.add(classificacao1);
            classificacoes.add(classificacao2);
            classificacoes.add(classificacao3);


            ProcessoSeriacao processoSeriacao = new ProcessoSeriacao(a, classificacoes, colaboradores);

            int expected = 2;

            int result = classificacoes.size();

            assertEquals(expected, result);
        }

}

*/
