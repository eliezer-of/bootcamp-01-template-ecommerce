package com.br.ecommerce.model;

import com.br.ecommerce.enums.GatewayPagamento;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Optional;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    @Valid
    private Produto produto;

    @Positive
    private int quantidade;

    @ManyToOne
    @NotNull
    @Valid
    private Usuario usuarioComprador;

    @Enumerated
    @NotNull
    private GatewayPagamento gatewayPagamento;
    
    @Deprecated
    public Compra(){
    }

    public Compra(@NotNull @Valid Produto produto, @Positive int quantidade, Optional<Usuario> usuarioComprador,
                  @NotNull GatewayPagamento gatewayPagamento) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.usuarioComprador = usuarioComprador.get();
        this.gatewayPagamento = gatewayPagamento;
    }

    public Long getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Usuario getUsuarioComprador() {
        return usuarioComprador;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", produto=" + produto +
                ", quantidade=" + quantidade +
                ", usuarioComprador=" + usuarioComprador +
                '}';
    }

    public ResponseEntity<?> urlRedirecionamento(UriComponentsBuilder uriComponentsBuilder) {
        return this.gatewayPagamento.criarUrlRetorno(this, uriComponentsBuilder);
    }
}
