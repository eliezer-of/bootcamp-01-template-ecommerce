package com.br.ecommerce.requests;

import com.br.ecommerce.annotations.UniqueValue;
import com.br.ecommerce.model.Categoria;

import javax.validation.constraints.NotBlank;

public class CategoriaRequest {

    @NotBlank
    @UniqueValue(fieldName = "nome", domainClass = Categoria.class, message = "Já existe uma categoria cadastrada!")
    private String nome;

    private Long idCategoriaMae;


    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCategoriaMae(Long categoriaMae) {
        this.idCategoriaMae = categoriaMae;
    }

    @Override
    public String toString() {
        return "CategoriaRequest{" +
                "nome='" + nome + '\'' +
                ", idCategoriaMae=" + idCategoriaMae +
                '}';
    }

    public Categoria toModel() {
       Categoria categoria = new Categoria(nome);
       if (idCategoriaMae != null) {
           categoria.setCategoriaMae(categoria);
       }
       return  categoria;
    }
}
