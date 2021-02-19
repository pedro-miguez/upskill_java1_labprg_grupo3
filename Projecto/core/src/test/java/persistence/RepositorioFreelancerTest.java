package persistence;

import domain.*;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class RepositorioFreelancerTest {

    @Test
    public void insertUtilizadorFreelancer() throws SQLException {
        ArrayList<HabilitacaoAcademica> habilitacaoAcademicas = new ArrayList<>();
        habilitacaoAcademicas.add(new HabilitacaoAcademica("Mestre", "Contabilidade", "ISCAP", 19.9));

        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"),
                "Programação de Jogos", "Programação de variados jogos em JAVA");

        CompetenciaTecnica ct = new CompetenciaTecnica(new CodigoUnico("JAV-01"), areaAtividade,
                "Programação Java", "Programação muito boa em Java", new ArrayList<>());

        ArrayList<ReconhecimentoCT> reconhecimentoCTS = new ArrayList<>();
        ReconhecimentoCT recoCT = new ReconhecimentoCT(ct,
                new GrauProficiencia(1, "Baixo"), new Data (2021, 02, 17));
        reconhecimentoCTS.add(recoCT);

        Freelancer freelancer = new Freelancer("Bob Marley", new Telefone(999999999), new Email("colab@org.com"),
                new NIF(214852581), reconhecimentoCTS, habilitacaoAcademicas);

        assertTrue(RepositorioFreelancer.getInstance().insertUtilizadorFreelancer(freelancer, "1234567898"));

    }

    @Test
    public void getFreelancerByEmail() throws SQLException {
        ArrayList<HabilitacaoAcademica> habilitacaoAcademicas = new ArrayList<>();
        habilitacaoAcademicas.add(new HabilitacaoAcademica("Mestre", "Contabilidade", "ISCAP", 19.9));

        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"),
                "Programação de Jogos", "Programação de variados jogos em JAVA");

        CompetenciaTecnica ct = new CompetenciaTecnica(new CodigoUnico("JAV-01"), areaAtividade,
                "Programação Java", "Programação muito boa em Java", new ArrayList<>());

        ArrayList<ReconhecimentoCT> reconhecimentoCTS = new ArrayList<>();
        ReconhecimentoCT recoCT = new ReconhecimentoCT(ct,
                new GrauProficiencia(1, "Baixo"), new Data (2021, 02, 17));
        reconhecimentoCTS.add(recoCT);

        Freelancer freelancer = new Freelancer("Bob Marley", new Telefone(999999999), new Email("colab@org.com"),
                new NIF(214852581), reconhecimentoCTS, habilitacaoAcademicas);

        assertEquals(freelancer, RepositorioFreelancer.getInstance().getFreelancerByEmail(new Email("colab@org.com")));
    }
}