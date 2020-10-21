package com.br.ecommerce.responses;

import com.br.ecommerce.model.Produto;

import java.math.BigDecimal;

public class ProdutoDetalheResponse {

    private String nomeProduto;
    private CaracteristicasProdutoResponse caracteristicas;
    private String descricao;
    private BigDecimal preco;
    private ImagensProdutoResponse imagens;

    public ProdutoDetalheResponse(Produto produto) {
        this.nomeProduto = produto.getNome();
        this.caracteristicas = new CaracteristicasProdutoResponse(produto.getCaracteristicas());
        this.descricao = produto.getDescricao();
        this.preco = produto.getValor();
        this.imagens = new ImagensProdutoResponse(produto.getImagemProdutos());
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

    public ImagensProdutoResponse getImagens() {
        return imagens;
    }
}
