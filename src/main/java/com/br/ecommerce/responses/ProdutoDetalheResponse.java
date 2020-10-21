package com.br.ecommerce.responses;

import com.br.ecommerce.model.Opiniao;
import com.br.ecommerce.model.Opinioes;
import com.br.ecommerce.model.Produto;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

public class ProdutoDetalheResponse {

    private String nomeProduto;
    private Set<CaracteristicasProdutoResponse> caracteristicas;
    private String descricao;
    private BigDecimal preco;
    private Set<String> imagens;
    private Set<Object> opinioes;

    public ProdutoDetalheResponse(Produto produto) {
        this.nomeProduto = produto.getNome();
        this.caracteristicas = produto.mapeiaCaracteristicas(CaracteristicasProdutoResponse::new);
        this.descricao = produto.getDescricao();
        this.preco = produto.getValor();
        this.imagens = produto.mapeiaImagens(imagem -> imagem.getLink());

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

    public Set<CaracteristicasProdutoResponse> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Set<String> getImagens() {
        return imagens;
    }

    public Set<Object> getOpinioes() {
        return opinioes;
    }
}
