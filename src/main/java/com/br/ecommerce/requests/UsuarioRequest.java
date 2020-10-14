package com.br.ecommerce.requests;

import com.br.ecommerce.annotations.UniqueValue;
import com.br.ecommerce.model.Usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioRequest {

    @Email
    @NotBlank
    @UniqueValue(fieldName = "login", domainClass = Usuario.class, message = "Já existe um email cadastrado com esse valor")
    private String login;

    @NotBlank
    @Size(min = 6)
    private String senha;

    public UsuarioRequest(@Email @NotBlank String login, @NotBlank @Size(min = 6) String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public Usuario toModel() {
        return new Usuario(login, senha);
    }
}
