package com.br.ecommerce.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    private LocalDateTime momentoCriacao;

    @ManyToOne
    @Valid
    @NotNull
    private Produto produto;

    @ManyToOne
    @Valid
    @NotNull
    private Usuario usuario;

    public Pergunta(@NotBlank String titulo, @Valid @NotNull Produto produto, @Valid @NotNull Usuario usuario) {
        this.titulo = titulo;
        this.produto = produto;
        this.usuario = usuario;
        this.momentoCriacao = LocalDateTime.now();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public Usuario getDonoProduto() {
        return produto.getDonoDoProduto() ;
    }

    public Produto getProduto() {
        return produto;
    }

    @Override
    public String toString() {
        return "Pergunta{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", momentoCriacao=" + momentoCriacao +
                ", produto=" + produto +
                ", usuario=" + usuario +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pergunta pergunta = (Pergunta) o;

        if (titulo != null ? !titulo.equals(pergunta.titulo) : pergunta.titulo != null) return false;
        if (produto != null ? !produto.equals(pergunta.produto) : pergunta.produto != null) return false;
        return usuario != null ? usuario.equals(pergunta.usuario) : pergunta.usuario == null;
    }

    @Override
    public int hashCode() {
        int result = titulo != null ? titulo.hashCode() : 0;
        result = 31 * result + (produto != null ? produto.hashCode() : 0);
        result = 31 * result + (usuario != null ? usuario.hashCode() : 0);
        return result;
    }
}
