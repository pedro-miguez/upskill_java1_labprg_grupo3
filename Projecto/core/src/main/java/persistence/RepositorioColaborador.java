package persistence;

import domain.Colaborador;
import domain.Email;
import domain.Organizacao;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class RepositorioColaborador {

    private static RepositorioColaborador instance;

    private RepositorioColaborador(){
        colaboradoresRegistados = new ArrayList<Colaborador>();
    }

    private List<Colaborador> colaboradoresRegistados;

    public static RepositorioColaborador getInstance(){
        if(instance == null){
            instance = new RepositorioColaborador();
        }
        return instance;
    }

    public void addColaborador(Colaborador colaborador) {
        this.colaboradoresRegistados.add(colaborador);
    }


    public Colaborador getColaboradorByEmail(Email email) {
        for (Colaborador c : colaboradoresRegistados) {
            if (c.getEmail().equals(email)) {
                return c;
            }
        }
        return null; //throw new ColaboradorNaoEncontradoException()
    }

    public ArrayList<Colaborador> getColaboradoresOrganizacao (Organizacao organizacao) {
        ArrayList<Colaborador> colaboradoresOrganizacao = new ArrayList<>();

        for (Colaborador c : colaboradoresRegistados) {
            if (c.getOrganizacao().equals(organizacao)) {
                colaboradoresOrganizacao.add(c);
            }
        }

        return colaboradoresOrganizacao;
    }

    public ArrayList<Colaborador> listarColaboradores() {
        return new ArrayList<Colaborador>(this.colaboradoresRegistados);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RepositorioColaborador that = (RepositorioColaborador) o;
        return colaboradoresRegistados.equals(that.colaboradoresRegistados);
    }

}
