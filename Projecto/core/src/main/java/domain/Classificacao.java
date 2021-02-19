package domain;

public class Classificacao {


    private Anuncio anuncio;
    private Freelancer freelancer;
    int lugar;

    public Classificacao(Anuncio anuncio, Freelancer freelancer, int lugar) {
        setAnuncio(anuncio);
        setFreelancer(freelancer);
        this.lugar = lugar;
    }

    public int getLugar() {
        return lugar;
    }

    public Anuncio getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(Anuncio anuncio) {
        if (anuncio != null)
            this.anuncio = anuncio;
        else
            throw new IllegalArgumentException("Anúncio Inválido");
    }

    public Freelancer getFreelancer() {
        return freelancer;
    }

    public void setFreelancer(Freelancer freelancer) {
        if (freelancer != null)
            this.freelancer = freelancer;
        else
            throw new IllegalArgumentException("Freelancer Inválido");
    }
}
