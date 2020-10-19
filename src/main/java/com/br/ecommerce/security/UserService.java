package com.br.ecommerce.security;

import org.springframework.security.core.context.SecurityContextHolder;

public class UserService {

    public static UsuarioLogado authenticated() {

        UsuarioLogado usuario = (UsuarioLogado) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (usuario instanceof UsuarioLogado) {
            return usuario;
        }

        return null;
    }
}
