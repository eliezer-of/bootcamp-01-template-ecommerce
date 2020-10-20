package com.br.ecommerce.requests;

import com.br.ecommerce.model.Opiniao;
import com.br.ecommerce.model.Produto;
import com.br.ecommerce.model.Usuario;
import org.springframework.util.Assert;

import javax.validation.constraints.*;
import java.util.Optional;

public class OpiniaoRequest {

    @Min(1)
    @Max(5)
    private int nota;

    @NotBlank
    private String titulo;

    @NotBlank
    @Size(max = 500)
    private String descricao;

    public OpiniaoRequest(@Min(1) @Max(5) int nota, @NotBlank String titulo, @NotBlank @Size(max = 500) String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Opiniao toModel(Produto produto, @NotNull Optional<Usuario> usuarioConsumidor) {
        Assert.isTrue(usuarioConsumidor.isPresent(), "Usuário optnante precisa ser válido!");
        return new Opiniao(nota, titulo, descricao, produto, usuarioConsumidor.get());
    }
}
