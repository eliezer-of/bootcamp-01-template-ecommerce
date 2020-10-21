package com.br.ecommerce.requests;

import com.br.ecommerce.model.Pergunta;
import com.br.ecommerce.model.Produto;
import com.br.ecommerce.model.Usuario;
import org.springframework.util.Assert;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class PerguntaRequest {

    @NotBlank
    private String titulo;
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Pergunta toModel(@NotNull @Valid Produto produto,@NotNull @Valid  Optional<Usuario> usuario) {
        Assert.isTrue(usuario.isPresent(), "Usuário deve ser válido");
        return new Pergunta(titulo, produto, usuario.get());
    }

}
