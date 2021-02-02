package domain;

import java.util.List;

public class CaracterizacaoCompTec {

    private CodigoUnico codigoUnico;
    private boolean obrigatorio;
    private GrauProficiencia grauProficiencia;


    public CaracterizacaoCompTec(CodigoUnico codigoUnico, boolean obrigatorio, GrauProficiencia grauProficiencia) {
        this.codigoUnico = codigoUnico;
        this.obrigatorio = obrigatorio;
        this.grauProficiencia = grauProficiencia;
    }

    public boolean isObrigatorio() {
        return obrigatorio;
    }

    public CodigoUnico getCodigoUnico() {
        return codigoUnico;
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
        return getCodigoUnico().equals(that.getCodigoUnico());
    }

}
