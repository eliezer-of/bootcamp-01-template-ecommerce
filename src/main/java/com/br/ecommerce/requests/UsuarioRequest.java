package com.br.ecommerce.requests;

import com.br.ecommerce.annotations.UniqueValue;
import com.br.ecommerce.model.Usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioRequest {

    @Email
    @NotBlank
    @UniqueValue(fieldName = "email", domainClass = Usuario.class, message = "Já existe um email cadastrado com esse valor")
    private String email;

    @NotBlank
    @Size(min = 6)
    private String senha;

    public UsuarioRequest(@Email @NotBlank String email, @NotBlank @Size(min = 6) String senha) {
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public Usuario toModel() {
        return new Usuario(email, senha);
    }
}
