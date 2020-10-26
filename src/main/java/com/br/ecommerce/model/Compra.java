package com.br.ecommerce.model;

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
    
    @Deprecated
    public Compra(){
    }

    public Compra(@NotNull @Valid Produto produto, @Positive int quantidade,
                  @NotNull @Valid Optional<Usuario> usuarioComprador) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.usuarioComprador = usuarioComprador.get();
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
}
