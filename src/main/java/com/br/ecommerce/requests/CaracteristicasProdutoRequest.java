package com.br.ecommerce.requests;

import com.br.ecommerce.model.CaracteristicasProduto;
import com.br.ecommerce.model.Produto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CaracteristicasProdutoRequest {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    public CaracteristicasProdutoRequest(@NotBlank String nome, @NotBlank String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return "CaracteristicasRequest{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }

    public CaracteristicasProduto toModel(@NotNull @Valid Produto produto){
        return new CaracteristicasProduto(nome, descricao, produto);
    }
}
