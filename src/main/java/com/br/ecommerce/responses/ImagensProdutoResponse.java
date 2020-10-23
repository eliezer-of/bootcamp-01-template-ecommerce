package com.br.ecommerce.responses;

import com.br.ecommerce.model.ImagemProduto;

import java.util.HashSet;
import java.util.Set;

public class ImagensProdutoResponse {

    Set<ImagemProduto> imagens = new HashSet<>();

    public ImagensProdutoResponse(Set<ImagemProduto> imagemProdutos) {
        this.imagens = imagemProdutos;
    }

    public Set<ImagemProduto> getImagens() {
        return imagens;
    }
}
