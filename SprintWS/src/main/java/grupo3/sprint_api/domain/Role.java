package grupo3.sprint_api.domain;

import java.util.Objects;

/**
 * Current class implements the tools to create new roles (as Role).
 * It has implementations to get a string representation through the toString()
 * method of the object.
 */
public class Role {

    private String descricao;
    private String designacao;

    public Role(String descricao, String designacao){
        this.descricao=descricao;
        this.designacao=designacao;
    }

    /**
     * Gets an description.
     * @return descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Sets an description.
     * @param descricao
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Gets an designation.
     * @return designacao
     */
    public String getDesignacao() {
        return designacao;
    }

    /**
     * Sets an designation.
     * @param designacao
     */
    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * The {@code equals} method implements an equivalence relation
     * on non-null object references:
     * @param o   the reference object with which to compare.
     * @return {@code true} if this object is the same as the obj
     *         argument; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role = (Role) o;
        return getDescricao().equals(role.getDescricao()) && getDesignacao().equals(role.getDesignacao());
    }

    /**
     * Returns a string representation of the object.
     * @return Returns a string representation of the object.
     */
    @Override
    public String toString() {
        return "Role{" +
                "descricao='" + descricao + '\'' +
                ", designacao='" + designacao + '\'' +
                '}';
    }
}
