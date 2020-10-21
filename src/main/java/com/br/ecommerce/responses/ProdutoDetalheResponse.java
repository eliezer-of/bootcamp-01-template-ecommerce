package com.br.ecommerce.responses;

import com.br.ecommerce.model.Opiniao;
import com.br.ecommerce.model.Opinioes;
import com.br.ecommerce.model.Produto;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

public class ProdutoDetalheResponse {

    private String nomeProduto;
    private CaracteristicasProdutoResponse caracteristicas;
    private String descricao;
    private BigDecimal preco;
    private ImagensProdutoResponse imagens;
    private Set<Object> opinioes;

    public ProdutoDetalheResponse(Produto produto) {
        this.nomeProduto = produto.getNome();
        this.caracteristicas = new CaracteristicasProdutoResponse(produto.getCaracteristicas());
        this.descricao = produto.getDescricao();
        this.preco = produto.getValor();
        this.imagens = new ImagensProdutoResponse(produto.getImagemProdutos());

        Opinioes opinioes = produto.getOpinioes();

        this.opinioes =  opinioes.mapeiaOpinioes(opiniao -> {
            return Map.of("nota",opiniao.getNota(),
                    "titulo",opiniao.getTitulo(),
                    "descricao",opiniao.getDescricao());
        });

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

    public Set<Object> getOpinioes() {
        return opinioes;
    }
}