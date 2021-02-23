package domain;

/**
 * Current class represents the tool for creating new rating of each of the 
 * applications.
 * 
 * @author Grupo 3
 */
public class Classificacao {


    private Anuncio anuncio;
    private Freelancer freelancer;
    int lugar;

    /**
     * Instantiates a new rating.
     * @param anuncio
     * @param freelancer
     * @param lugar 
     */
    public Classificacao(Anuncio anuncio, Freelancer freelancer, int lugar) {
        setAnuncio(anuncio);
        setFreelancer(freelancer);
        this.lugar = lugar;
    }

    /**
     * Gets the place.
     * @return lugar
     */
    public int getLugar() {
        return lugar;
    }

    /**
     * Gets the advertisement.
     * @return anuncio
     */
    public Anuncio getAnuncio() {
        return anuncio;
    }

    /**
     * Sets the advertisement.
     * @param anuncio 
     */
    public void setAnuncio(Anuncio anuncio) {
        if (anuncio != null)
            this.anuncio = anuncio;
        else
            throw new IllegalArgumentException("Anúncio Inválido");
    }

    /**
     * Gets the freelancer.
     * @return freelancer
     */
    public Freelancer getFreelancer() {
        return freelancer;
    }

    /**
     * Sets the freelancer.
     * @param freelancer 
     */
    public void setFreelancer(Freelancer freelancer) {
        if (freelancer != null)
            this.freelancer = freelancer;
        else
            throw new IllegalArgumentException("Freelancer Inválido");
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
        if (!(o instanceof Classificacao)) return false;

        Classificacao that = (Classificacao) o;

        if (getLugar() != that.getLugar()) return false;
        if (getAnuncio() != null ? !getAnuncio().equals(that.getAnuncio()) : that.getAnuncio() != null) return false;
        return getFreelancer() != null ? getFreelancer().equals(that.getFreelancer()) : that.getFreelancer() == null;
    }

}
