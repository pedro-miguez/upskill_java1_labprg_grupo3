package domain;

import java.io.Serializable;
import java.util.Objects;

public class Telefone implements Serializable {

    private int number;

    /**
     * Construtor de um número de Telefone com o seguinte parâmetro:
     *
     * @param number
     */
    public Telefone(int number){
        setTelefone(number);
    }

    /**
     * Método para obtenção do número de telefone
     *
     * @return
     */
    public int getTelefone() {
        return this.number;
    }

    /**
     * Valida o número de telefone
     *
     * @param number
     */
    private void setTelefone(int number) {
        if (number < 100000000 || number > 999999999) {
            throw new IllegalArgumentException("Número de telefone inválido");
        } else {
            this.number = number;
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
        if (o == null || getClass() != o.getClass()) return false;
        Telefone telefone = (Telefone) o;
        return number == telefone.number;
    }

}
