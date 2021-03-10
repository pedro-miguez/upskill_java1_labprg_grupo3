package grupo3.sprint_api.domain;

/**
 * Current class implements the tools to create new roles (as Role).
 * It has implementations to get a string representation through the toString()
 * method of the object.
 */
public class Role {

    private String rolename;
    private String descricao;

    /**
     * Constructor of the class Role.
     *
     * @param rolename
     * @param descricao
     */
    public Role(String rolename, String descricao){
        this.rolename = rolename;
        this.descricao = descricao;
    }

    /**
     * Gets an description.
     *
     * @return descricao
     */
    public String getRolename() {
        return rolename;
    }

    /**
     * Sets an description.
     *
     * @param rolename
     */
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    /**
     * Gets an designation.
     *
     * @return designacao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Sets an designation.
     *
     * @param descricao
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
        return getRolename().equals(role.getRolename()) && getDescricao().equals(role.getDescricao());
    }

    /**
     * Returns a string representation of the object.
     *
     * @return Returns a string representation of the object.
     */
    @Override
    public String toString() {
        return "Role{" +
                "descricao='" + rolename + '\'' +
                ", designacao='" + descricao + '\'' +
                '}';
    }
}
