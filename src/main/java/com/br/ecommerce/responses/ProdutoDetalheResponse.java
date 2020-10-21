package com.br.ecommerce.responses;

import com.br.ecommerce.model.CaracteristicasProduto;
import com.br.ecommerce.model.Produto;

import java.math.BigDecimal;
import java.util.Set;

public class ProdutoDetalheResponse {

    private String nomeProduto;
    private Set<CaracteristicasProduto> caracteristicas;
    private String descricao;
    private BigDecimal preco;

    public ProdutoDetalheResponse(Produto produto) {
        this.nomeProduto = produto.getNome();
        this.caracteristicas = produto.getCaracteristicas();
        this.descricao = produto.getDescricao();
        this.preco = produto.getValor();
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public Set<CaracteristicasProduto> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }
}
