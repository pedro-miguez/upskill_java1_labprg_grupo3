package domain;

import java.io.Serializable;

/**
 * Current class implements the tools for creating new recognition of technical skills. 
 * 
 * @author Grupo 3
 */
public class ReconhecimentoCT implements Serializable {

    private CompetenciaTecnica competenciaTecnica;
    private GrauProficiencia grauProficiencia;
    private Data dataReconhecimento;

    /**
     * Instantiates a new recognition of technical skill.
     * 
     * @param competenciaTecnica
     * @param grauProficiencia
     * @param dataReconhecimento 
     */
    public ReconhecimentoCT(CompetenciaTecnica competenciaTecnica, 
                            GrauProficiencia grauProficiencia, Data dataReconhecimento) {
        
        setCompetenciaTecnica(competenciaTecnica);
        setGrauProficiencia(grauProficiencia);
        setDataReconhecimento(dataReconhecimento);
    }

    /**
     * Gets the technical skill.
     * @return competenciaTecnica
     */
    public CompetenciaTecnica getCompetenciaTecnica() {
        return competenciaTecnica;
    }

    /**
     * Gets the degree of proficiency.
     * @return grauProficiencia
     */
    public GrauProficiencia getGrauProficiencia() {
        return grauProficiencia;
    }

    /**
     * Gets the date of the recognition.
     * @return dataReconhecimento
     */
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
        if (!(o instanceof ReconhecimentoCT)) return false;
        ReconhecimentoCT that = (ReconhecimentoCT) o;
        return getCompetenciaTecnica().equals(that.getCompetenciaTecnica()) &&
                getGrauProficiencia().equals(that.getGrauProficiencia()) && 
                getDataReconhecimento().equals(that.getDataReconhecimento());
    }

    
    /**
     * Returns a string representation with very concise but precise information 
     * about the object and its attributes.
     *
     * @return a string representation of the object (recognition of technical skills).
     *
     */
    @Override
    public String toString() {
        return competenciaTecnica.getDescricao() +
                "%n" + grauProficiencia;
    }
}
