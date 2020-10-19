package com.br.ecommerce.requests;

import com.br.ecommerce.annotations.ExistsValue;
import com.br.ecommerce.model.Categoria;
import com.br.ecommerce.model.Produto;
import com.br.ecommerce.model.Usuario;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProdutoRequest {

    @NotBlank
    private String nome;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotNull
    @Positive
    private Integer quantidadeDisponivel;

    @NotBlank
    @Size(max = 1000)
    private String descricao;

    @NotNull
    @ExistsValue(fieldName = "id", domainClass = Categoria.class, message = "A Categoria precisa estar cadastrada!")
    private Long idCategoria;

    @Size(min = 3)
    private List<CaracteristicasProdutoRequest> caracteristicas = new ArrayList<>();

    public ProdutoRequest(@NotBlank String nome, @NotNull @Positive BigDecimal valor,
                          @NotNull @Positive Integer quantidadeDisponivel, @NotBlank @Size(max = 1000) String descricao,
                          @NotNull Long idCategoria, @Valid @Size(min = 3) List<CaracteristicasProdutoRequest> caracteristicas) {
        this.nome = nome;
        this.valor = valor;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.descricao = descricao;
        this.idCategoria = idCategoria;
        this.caracteristicas.addAll(caracteristicas);
    }

    public List<CaracteristicasProdutoRequest> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<CaracteristicasProdutoRequest> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    @Override
    public String toString() {
        return "ProdutoRequest{" +
                "nome='" + nome + '\'' +
                ", valor=" + valor +
                ", quantidadeDisponivel=" + quantidadeDisponivel +
                ", descricao='" + descricao + '\'' +
                ", idCategoria=" + idCategoria +
                ", caracteristicas=" + caracteristicas +
                '}';
    }

    public Produto toModel(EntityManager manager, Optional<Usuario> usuario) {

        Categoria categoria = manager.find(Categoria.class, idCategoria);
        return new Produto(nome, valor, quantidadeDisponivel, caracteristicas, descricao, categoria, usuario);
    }

}


