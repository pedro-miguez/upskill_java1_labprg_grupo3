package domain;

import java.io.Serializable;
import java.util.Objects;

public class HabilitacaoAcademica implements Serializable {

    private String grau;
    private String designacaoCurso;
    private String nomeInstituicao;
    private double mediaCurso;

    public HabilitacaoAcademica(String grau, String designacaoCurso, String nomeInstituicao, double mediaCurso) {
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
        if (mediaCurso < 0 || mediaCurso > 20) {
            throw new IllegalArgumentException("Número de telefone inválido");
        } else {
        }
        this.mediaCurso = mediaCurso;
    }

    public String getGrau() {
        return grau;
    }

    public String getDesignacaoCurso() {
        return designacaoCurso;
    }

    public String getNomeInstituicao() {
        return nomeInstituicao;
    }

    public double getMediaCurso() {
        return mediaCurso;
    }

    @Override
    public String toString() {
        return String.format("Freelancer: %s%Grau: %s%nDesignacaoCurso: %s%NomeInstituicao: %s%nMediaCurso: %d",
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
        return Double.compare(that.getMediaCurso(), getMediaCurso()) == 0 && getGrau().equals(that.getGrau()) && getDesignacaoCurso().equals(that.getDesignacaoCurso()) && getNomeInstituicao().equals(that.getNomeInstituicao());
    }

}
