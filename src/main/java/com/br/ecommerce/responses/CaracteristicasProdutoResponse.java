package com.br.ecommerce.responses;

import com.br.ecommerce.model.CaracteristicasProduto;

public class CaracteristicasProdutoResponse {


    private String nome;
    private String descricao;


    public CaracteristicasProdutoResponse(CaracteristicasProduto caracteristicasProduto) {
        this.nome = caracteristicasProduto.getNome();
        this.descricao = caracteristicasProduto.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
