package domain;

import java.util.Objects;

public class ReconhecimentoCT {

    CompetenciaTecnica ct;
    Freelancer fr;
    GrauProficiencia gp;
    Data dataReconhecimento;

    public ReconhecimentoCT(CompetenciaTecnica ct, Freelancer fr, GrauProficiencia gp, Data dataReconhecimento) {
        this.ct = ct;
        this.fr = fr;
        this.gp = gp;
        this.dataReconhecimento = dataReconhecimento;
    }

    public CompetenciaTecnica getCt() {
        return ct;
    }

    public Freelancer getFr() {
        return fr;
    }

    public GrauProficiencia getGp() {
        return gp;
    }

    public Data getDataReconhecimento() {
        return dataReconhecimento;
    }

    public void setCt(CompetenciaTecnica ct) {
        this.ct = ct;
    }

    public void setFr(Freelancer fr) {
        this.fr = fr;
    }

    public void setGp(GrauProficiencia gp) {
        this.gp = gp;
    }

    public void setDataReconhecimento(Data dataReconhecimento) {
        this.dataReconhecimento = dataReconhecimento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReconhecimentoCT)) return false;
        ReconhecimentoCT that = (ReconhecimentoCT) o;
        return getCt().equals(that.getCt()) && getFr().equals(that.getFr()) &&
                getGp().equals(that.getGp()) && getDataReconhecimento().equals(that.getDataReconhecimento());
    }

    }

