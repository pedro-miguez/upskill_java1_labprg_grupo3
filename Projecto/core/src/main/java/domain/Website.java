package domain;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Website implements Serializable {

    private String website;


    /**
     * Construtor para o website com o seguinte parâmetro:
     *
     * @param website
     */
    public Website(String website) {
        setWebsite(website);
    }

    /**
     * Método para obter o website
     *
     * @return website
     */
    public String getWebsite() {
        return website;
    }

    /**
     * Valida o website
     *
     * @param website
     */
    private void setWebsite(String website) {
        String regex = "(http:\\/\\/|https:\\/\\/)?(www.)?([a-zA-Z]+.[a-zA-Z]*.[a-z0-9]+)\\.(([a-z]){2,3})?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(website);
        if (matcher.matches()){
            this.website = website;
        } else {
            throw new IllegalArgumentException("O website introduzido é inválido.");
        }
    }

    /**
     * Método para verificar se dois objetos são iguais
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Website)) return false;

        Website website1 = (Website) o;

        return getWebsite().equals(website1.getWebsite());
    }

}
