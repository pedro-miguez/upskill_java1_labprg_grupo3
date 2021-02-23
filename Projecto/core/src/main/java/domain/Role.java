package domain;

/**
 * Current class is an Enum class that gives us the options available for the 
 * roles that a user of the platform (Plataforma) can have.
 */
public enum Role {
    /**
     * Manager (GESTOR) role, for the organizations (Organizacao) managers.
     */
    GESTOR,
    
    /**
     * Collaborator (COLABORADOR) role, for the organizations (Organizacao) collaborators.
     */
    COLABORADOR,
    
    /**
     * Administrative (ADMINISTRATIVO) role, for the platform (Plataforma) administrative.
     */
    ADMINISTRATIVO,

    /**
     * Freelancer role.
     */
    FREELANCER
}
