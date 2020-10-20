package com.br.ecommerce.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Entity
public class Opniao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(1)
    @Max(5)
    private int nota;

    @NotBlank
    private String titulo;

    @NotBlank
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
    public Opniao() {}

    public Opniao(@Min(1) @Max(5) int nota, @NotBlank String titulo, @NotBlank String descricao,
                  @Valid @NotNull Produto produto, @Valid @NotNull Usuario usuario) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.produto = produto;
        this.usuario = usuario;
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

        Opniao opniao = (Opniao) o;

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
