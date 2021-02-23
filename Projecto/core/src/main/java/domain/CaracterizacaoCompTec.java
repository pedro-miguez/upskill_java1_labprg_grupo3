package domain;

import java.io.Serializable;

/**
 * Technical competence characterization class allows to set a kind of mastery 
 * to the technical competence that may be (or not) mandatory for the task(s) 
 * in hand. This will be defined by the Platform administrative accordingly.
 */
public class CaracterizacaoCompTec implements Serializable {

    private CompetenciaTecnica competenciaTecnica;

    private boolean obrigatorio;

    private GrauProficiencia grauProficiencia;


    /**
     * Instantiates a new technical competence characterization.
     *
     * @param competenciaTecnica as Technical competence
     * @param obrigatorio        as mandatory
     * @param grauProficiencia   as mastery level
     */
    public CaracterizacaoCompTec(CompetenciaTecnica competenciaTecnica, 
                                    boolean obrigatorio, GrauProficiencia grauProficiencia) {
        this.competenciaTecnica = competenciaTecnica;
        this.obrigatorio = obrigatorio;
        this.grauProficiencia = grauProficiencia;
    }

    /**
     * Determines if a technical competence is mandatory or not.
     *
     * @return if mandatory or not.
     */
    public boolean isObrigatorio() {
        return obrigatorio;
    }

    /**
     * Gets the unique reference of the technical competence.
     *
     * @return the unique reference.
     */
    public CodigoUnico getCodigoUnicoCompTec() {
        return this.competenciaTecnica.getCodigoUnico();
    }

    /**
     * Gets the technical competence.
     *
     * @return the technical competence.
     */
    public CompetenciaTecnica getCompetenciaTecnica() {
        return this.competenciaTecnica;
    }

    /**
     * Gets grade of mastery.
     *
     * @return the grade of mastery.
     */
    public GrauProficiencia getGrauProficiencia() {
        return grauProficiencia;
    }


    /**
     * Returns a string representation of the object characterization of technical 
     * competence and its attributes.
     *
     * The result should be a concise but informative representation that is easy
     * for a person to read.
     * It is overriden by all subclasses.
     *
     * @return  a string representation of the object.
     *
     */
    @Override
    public String toString() {
        return  competenciaTecnica.getDescricao() +
                (obrigatorio ? "\nObrigatória " : "\nOpcional") +
                "\nGrau de Proficiência: " + grauProficiencia.getDesignacao();
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * The {@code equals} method implements an equivalence relation
     * on non-null object references:
     *
     * @param   o   the reference object with which to compare.
     * @return  {@code true} if this object is the same as the obj
     *          argument; {@code false} otherwise.
     *
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CaracterizacaoCompTec)) return false;
        if (!super.equals(o)) return false;
        CaracterizacaoCompTec that = (CaracterizacaoCompTec) o;
        return this.competenciaTecnica.equals(that.competenciaTecnica);
    }

}
