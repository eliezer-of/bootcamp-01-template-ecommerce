package com.br.ecommerce.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class CaracteristicasProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    @ManyToOne
    @NotNull
    @Valid
    private Produto produto;

    @Deprecated
    public CaracteristicasProduto(){

    }

    public CaracteristicasProduto(@NotBlank String nome, @NotBlank String valor, @NotNull @Valid Produto produto) {
        this.nome = nome;
        this.descricao = valor;
        this.produto = produto;
    }

    @Override
    public String toString() {
        return "CaracteristicasProduto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }

}
