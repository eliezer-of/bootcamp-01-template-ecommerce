package com.br.ecommerce.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

@Entity
public class Opiniao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(1)
    @Max(5)
    private int nota;

    @NotBlank
    private String titulo;

    @NotBlank
    @Size(max = 500)
    private String descricao;

    @ManyToOne
    @Valid
    @NotNull
    private Produto produto;

    @ManyToOne
    @Valid
    @NotNull
    private Usuario usuario;

    @Deprecated
    public Opiniao() {}

    public Opiniao(@Min(1) @Max(5) int nota, @NotBlank String titulo, @Size(max = 500) @NotBlank String descricao,
                   @Valid @NotNull Produto produto, @Valid @NotNull Usuario usuario) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.produto = produto;
        this.usuario = usuario;
    }

    public int getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return "Opniao{" +
                "id=" + id +
                ", nota=" + nota +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", produto=" + produto +
                ", usuario=" + usuario +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Opiniao opniao = (Opiniao) o;

        if (titulo != null ? !titulo.equals(opniao.titulo) : opniao.titulo != null) return false;
        if (descricao != null ? !descricao.equals(opniao.descricao) : opniao.descricao != null) return false;
        if (produto != null ? !produto.equals(opniao.produto) : opniao.produto != null) return false;
        return usuario != null ? usuario.equals(opniao.usuario) : opniao.usuario == null;
    }

    @Override
    public int hashCode() {
        int result = titulo != null ? titulo.hashCode() : 0;
        result = 31 * result + (descricao != null ? descricao.hashCode() : 0);
        result = 31 * result + (produto != null ? produto.hashCode() : 0);
        result = 31 * result + (usuario != null ? usuario.hashCode() : 0);
        return result;
    }
}
