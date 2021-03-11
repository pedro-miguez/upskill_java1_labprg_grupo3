package domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * Current class implements the tool for creating new academic qualifications.
 * 
 * @author Grupo 3
 */
public class HabilitacaoAcademica implements Serializable {

    private String grau;
    private String designacaoCurso;
    private String nomeInstituicao;
    private double mediaCurso;

    
    /**
     * Instantiates a new academic qualification.
     * 
     * @param grau
     * @param designacaoCurso
     * @param nomeInstituicao
     * @param mediaCurso 
     */
    public HabilitacaoAcademica(String grau, String designacaoCurso, String nomeInstituicao, 
                                double mediaCurso) {
        setGrau(grau);
        setDesignacaoCurso(designacaoCurso);
        setNomeInstituicao(nomeInstituicao);
        setMediaCurso(mediaCurso);
    }


    private void setGrau(String grau) {
        if (grau.length() < 1 || grau.trim().isEmpty()) {
            throw new IllegalArgumentException("Designacao invalida");
        }
        this.grau = grau;
    }

    private void setDesignacaoCurso(String designacaoCurso) {
        if (designacaoCurso.length() < 2 || designacaoCurso.trim().isEmpty()) {
            throw new IllegalArgumentException("Designacao invalida");
        }
        this.designacaoCurso = designacaoCurso;
    }

    private void setNomeInstituicao(String nomeInstituicao) {
        if (nomeInstituicao.length() < 2 || nomeInstituicao.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da instituicao invalido");
        }
        this.nomeInstituicao = nomeInstituicao;
    }

    private void setMediaCurso(double mediaCurso) {
        if (mediaCurso < 9.5 || mediaCurso > 20) {
            throw new IllegalArgumentException("Número de telefone inválido");
        } else {
        }
        this.mediaCurso = mediaCurso;
    }

    
    /**
     * Gets the level of qualification.
     * 
     * @return grau
     */
    public String getGrau() {
        return grau;
    }

    /**
     * Gets the course designation.
     * 
     * @return designacaoCurso
     */
    public String getDesignacaoCurso() {
        return designacaoCurso;
    }

    /**
     * Gets the Institution Name.
     * 
     * @return nomeInstituicao
     */
    public String getNomeInstituicao() {
        return nomeInstituicao;
    }

    /**
     * Gets the final course average.
     * 
     * @return mediaCurso
     */
    public double getMediaCurso() {
        return mediaCurso;
    }

    /**
     * Returns a string representation of the object academic qualifications.
     * 
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return String.format("Habilitacao Academica: %nGrau: %s%nDesignacaoCurso: %s%nNomeInstituicao: %s%nMediaCurso: %.2f",
                this.grau, this.designacaoCurso, this.nomeInstituicao, this.mediaCurso);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * The {@code equals} method implements an equivalence relation
     * on non-null object references:
     *
     * @param o the reference object with which to compare.
     * @return {@code true} if this object is the same as the obj
     * argument; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HabilitacaoAcademica)) return false;
        HabilitacaoAcademica that = (HabilitacaoAcademica) o;
        return Double.compare(that.getMediaCurso(), getMediaCurso()) == 0 && 
                getGrau().equals(that.getGrau()) && 
                getDesignacaoCurso().equals(that.getDesignacaoCurso()) && 
                getNomeInstituicao().equals(that.getNomeInstituicao());
    }

}
