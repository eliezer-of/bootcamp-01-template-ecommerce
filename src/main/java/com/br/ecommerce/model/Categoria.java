package com.br.ecommerce.model;

import com.br.ecommerce.annotations.UniqueValue;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @UniqueValue(fieldName = "nome", domainClass = Categoria.class, message = "Já existe uma categoria cadastrada!")
    private String nome;

    @ManyToOne()
    private Categoria categoriaMae;

    @Deprecated
    public Categoria(){}

    public Categoria(@NotBlank String nome) {
        this.nome = nome;
    }

    public void setCategoriaMae(Categoria categoriaMae) {
        this.categoriaMae = categoriaMae;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", categoriaMae=" + categoriaMae +
                '}';
    }
}
