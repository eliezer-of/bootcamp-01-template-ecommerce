package com.br.ecommerce.requests;

import com.br.ecommerce.annotations.ExistsValue;
import com.br.ecommerce.annotations.UniqueValue;
import com.br.ecommerce.model.Categoria;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CategoriaRequest {

    @NotBlank
    @UniqueValue(fieldName = "nome", domainClass = Categoria.class, message = "Já existe uma categoria cadastrada!")
    private String nome;

    @Positive
    private Long idCategoriaMae;


    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdCategoriaMae(Long idCategoriaMae) {
        this.idCategoriaMae = idCategoriaMae;
    }

    @Override
    public String toString() {
        return "CategoriaRequest{" +
                "nome='" + nome + '\'' +
                ", idCategoriaMae=" + idCategoriaMae +
                '}';
    }

    public Categoria toModel(EntityManager manager) {

        Categoria categoria = new Categoria(nome);

        if(idCategoriaMae != null) {
            Categoria categoriaMae = manager.find(Categoria.class,idCategoriaMae);
            Assert.notNull(categoriaMae, "O id da categoria mãe precisa ser válido");

            categoria.setCategoriaMae(categoriaMae);
        }
        return categoria;
    }
}
