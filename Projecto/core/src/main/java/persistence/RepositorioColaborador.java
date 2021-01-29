package persistence;

import exceptions.EmailNaoAssociadoAColaboradorException;
import domain.Colaborador;
import domain.Email;
import domain.Organizacao;

import java.util.ArrayList;
import java.util.List;

public class RepositorioColaborador {

    private static RepositorioColaborador instance;

    private RepositorioColaborador(){
        colaboradoresRegistados = new ArrayList<>();
    }

    private List<Colaborador> colaboradoresRegistados;

    public static RepositorioColaborador getInstance(){
        if(instance == null){
            instance = new RepositorioColaborador();
        }
        return instance;
    }

    public boolean addColaborador(Colaborador colaborador) {
        if (this.colaboradoresRegistados.contains(colaborador)) {
            return false;
        } else {
            return this.colaboradoresRegistados.add(colaborador);
        }
    }


    public Colaborador getColaboradorByEmail(Email email) {
        for (Colaborador c : colaboradoresRegistados) {
            if (c.getEmail().equals(email)) {
                return c;
            }
        }

        throw new EmailNaoAssociadoAColaboradorException(email.toString() + " não está associado a nenhum colaborador");

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
        return new ArrayList<>(this.colaboradoresRegistados);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RepositorioColaborador that = (RepositorioColaborador) o;
        return colaboradoresRegistados.equals(that.colaboradoresRegistados);
    }

}
