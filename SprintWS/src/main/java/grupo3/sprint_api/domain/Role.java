package grupo3.sprint_api.domain;

import java.util.Objects;

public class Role {

    private String descricao;
    private String designacao;

    public Role(String descricao, String designacao){
        this.descricao=descricao;
        this.designacao=designacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role = (Role) o;
        return getDescricao().equals(role.getDescricao()) && getDesignacao().equals(role.getDesignacao());
    }

    @Override
    public String toString() {
        return "Role{" +
                "descricao='" + descricao + '\'' +
                ", designacao='" + designacao + '\'' +
                '}';
    }
}
