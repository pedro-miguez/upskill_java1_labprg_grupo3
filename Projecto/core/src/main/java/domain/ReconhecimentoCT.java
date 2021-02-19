package domain;

import java.io.Serializable;

public class ReconhecimentoCT implements Serializable {

    private CompetenciaTecnica competenciaTecnica;
    private GrauProficiencia grauProficiencia;
    private Data dataReconhecimento;

    public ReconhecimentoCT(CompetenciaTecnica competenciaTecnica, GrauProficiencia grauProficiencia, Data dataReconhecimento) {
        setCompetenciaTecnica(competenciaTecnica);
        setGrauProficiencia(grauProficiencia);
        setDataReconhecimento(dataReconhecimento);
    }

    public CompetenciaTecnica getCompetenciaTecnica() {
        return competenciaTecnica;
    }

    public GrauProficiencia getGrauProficiencia() {
        return grauProficiencia;
    }

    public Data getDataReconhecimento() {
        return dataReconhecimento;
    }

    private void setCompetenciaTecnica(CompetenciaTecnica competenciaTecnica) {
        this.competenciaTecnica = competenciaTecnica;
    }

    private void setGrauProficiencia(GrauProficiencia grauProficiencia) {
        this.grauProficiencia = grauProficiencia;
    }

    private void setDataReconhecimento(Data dataReconhecimento) {
        this.dataReconhecimento = dataReconhecimento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReconhecimentoCT)) return false;
        ReconhecimentoCT that = (ReconhecimentoCT) o;
        return getCompetenciaTecnica().equals(that.getCompetenciaTecnica()) &&
                getGrauProficiencia().equals(that.getGrauProficiencia()) && getDataReconhecimento().equals(that.getDataReconhecimento());
    }

    @Override
    public String toString() {
        return "ReconhecimentoCT{" +
                "competenciaTecnica=" + competenciaTecnica +
                ", grauProficiencia=" + grauProficiencia +
                ", dataReconhecimento=" + dataReconhecimento +
                '}';
    }

}

