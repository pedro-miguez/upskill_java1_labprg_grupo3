package domain;

public class ReconhecimentoCT {

    CompetenciaTecnica competenciaTecnica;
    Freelancer freelancer;
    GrauProficiencia grauProficiencia;
    Data dataReconhecimento;

    public ReconhecimentoCT(CompetenciaTecnica competenciaTecnica, Freelancer freelancer, GrauProficiencia grauProficiencia, Data dataReconhecimento) {
        setCompetenciaTecnica(competenciaTecnica);
        setFreelancer(freelancer);
        setGrauProficiencia(grauProficiencia);
        setDataReconhecimento(dataReconhecimento);
    }

    public CompetenciaTecnica getCompetenciaTecnica() {
        return competenciaTecnica;
    }

    public Freelancer getFreelancer() {
        return freelancer;
    }

    public GrauProficiencia getGrauProficiencia() {
        return grauProficiencia;
    }

    public Data getDataReconhecimento() {
        return dataReconhecimento;
    }

    public void setCompetenciaTecnica(CompetenciaTecnica competenciaTecnica) {
        this.competenciaTecnica = competenciaTecnica;
    }

    public void setFreelancer(Freelancer freelancer) {
        this.freelancer = freelancer;
    }

    public void setGrauProficiencia(GrauProficiencia grauProficiencia) {
        this.grauProficiencia = grauProficiencia;
    }

    public void setDataReconhecimento(Data dataReconhecimento) {
        this.dataReconhecimento = dataReconhecimento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReconhecimentoCT)) return false;
        ReconhecimentoCT that = (ReconhecimentoCT) o;
        return getCompetenciaTecnica().equals(that.getCompetenciaTecnica()) && getFreelancer().equals(that.getFreelancer()) &&
                getGrauProficiencia().equals(that.getGrauProficiencia()) && getDataReconhecimento().equals(that.getDataReconhecimento());
    }

    @Override
    public String toString() {
        return "ReconhecimentoCT{" +
                "competenciaTecnica=" + competenciaTecnica +
                ", freelancer=" + freelancer +
                ", grauProficiencia=" + grauProficiencia +
                ", dataReconhecimento=" + dataReconhecimento +
                '}';
    }
}

