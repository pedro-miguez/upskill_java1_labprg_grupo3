package persistence;

import exceptions.EmailNaoAssociadoAColaboradorException;
import domain.Colaborador;
import domain.Email;
import domain.Organizacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * The type RepositorioColaborador.
 */
public class RepositorioColaborador implements Serializable {

    private static RepositorioColaborador instance;

    /**
     * Colaboradores que irão ser adicionados no repositório.
     */
    private RepositorioColaborador(){
        colaboradoresRegistados = new ArrayList<>();
    }

    private List<Colaborador> colaboradoresRegistados;

    /**
     * Método estático que devolve uma referência única do objecto da classe,
     * que implementa um singleton.
     * @return 
     */
    public static RepositorioColaborador getInstance(){
        if(instance == null){
            instance = new RepositorioColaborador();
        }
        return instance;
    }

    /**
     * Método booleano que verifica se um colaborador existe no repositório,
     * caso contrário é adicionado ao mesmo.
     * @param colaborador
     * @return 
     */
    public boolean addColaborador(Colaborador colaborador) {
        if (this.colaboradoresRegistados.contains(colaborador)) {
            return false;
        } else {
            return this.colaboradoresRegistados.add(colaborador);
        }
    }

    /**
     * Método para obtenção de um colaborador através do seu email.
     * @param email
     * @return 
     */
    public Colaborador getColaboradorByEmail(Email email) {
        for (Colaborador c : colaboradoresRegistados) {
            if (c.getEmail().equals(email)) {
                return c;
            }
        }

        throw new EmailNaoAssociadoAColaboradorException(email.toString() + " não está associado a nenhum colaborador");

    }

    /**
     * Método para obtenção de um colaborador de uma dada organização.
     * @param organizacao
     * @return 
     */
    public ArrayList<Colaborador> getColaboradoresOrganizacao (Organizacao organizacao) {
        ArrayList<Colaborador> colaboradoresOrganizacao = new ArrayList<>();

        for (Colaborador c : colaboradoresRegistados) {
            if (c.getOrganizacao().equals(organizacao)) {
                colaboradoresOrganizacao.add(c);
            }
        }

        return colaboradoresOrganizacao;
    }

    /**
     * Método para listar colaboradores.
     * @return 
     */
    public ArrayList<Colaborador> listarColaboradores() {
        return new ArrayList<>(this.colaboradoresRegistados);
    }

    /**
     * Método para verificar se dois objectos (neste caso, colaboradores) são
     * iguais.
     * @param o
     * @return 
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RepositorioColaborador that = (RepositorioColaborador) o;
        return colaboradoresRegistados.equals(that.colaboradoresRegistados);
    }

}
