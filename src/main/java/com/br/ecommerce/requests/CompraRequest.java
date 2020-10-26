package com.br.ecommerce.requests;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CompraRequest {

    @Positive
    private int quantidade;

    @NotNull
    private Long idProduto;

    public CompraRequest(@Positive int quantidade, @NotNull Long idProduto) {
        this.quantidade = quantidade;
        this.idProduto = idProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Long getIdProduto() {
        return idProduto;
    }
}
