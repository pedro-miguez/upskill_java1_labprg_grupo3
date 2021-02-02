package domain;

import java.util.List;

public class CaracterizacaoCompTec {

    private CompetenciaTecnica competenciaTecnica;
    private boolean obrigatorio;
    private GrauProficiencia grauProficiencia;


    public CaracterizacaoCompTec(CompetenciaTecnica competenciaTecnica, boolean obrigatorio, GrauProficiencia grauProficiencia) {
        this.competenciaTecnica = competenciaTecnica;
        this.obrigatorio = obrigatorio;
        this.grauProficiencia = grauProficiencia;
    }

    public boolean isObrigatorio() {
        return obrigatorio;
    }

    public CodigoUnico getCodigoUnicoCompTec() {
        return this.competenciaTecnica.getCodigoUnico();
    }

    public CompetenciaTecnica getCompetenciaTecnica() {
        return this.competenciaTecnica;
    }

    public GrauProficiencia getGrauProficiencia() {
        return grauProficiencia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CaracterizacaoCompTec)) return false;
        if (!super.equals(o)) return false;
        CaracterizacaoCompTec that = (CaracterizacaoCompTec) o;
        return this.competenciaTecnica.equals(that.competenciaTecnica);
    }

}
