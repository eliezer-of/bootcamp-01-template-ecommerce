package com.br.ecommerce.responses;

import com.br.ecommerce.model.CaracteristicasProduto;
import com.br.ecommerce.model.Produto;

import java.math.BigDecimal;
import java.util.Set;

public class ProdutoDetalheResponse {

    private String nomeProduto;
    private CaracteristicasProdutoResponse caracteristicas;
    private String descricao;
    private BigDecimal preco;

    public ProdutoDetalheResponse(Produto produto) {
        this.nomeProduto = produto.getNome();
        this.caracteristicas = new CaracteristicasProdutoResponse(produto.getCaracteristicas());
        this.descricao = produto.getDescricao();
        this.preco = produto.getValor();
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public CaracteristicasProdutoResponse getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }
}
