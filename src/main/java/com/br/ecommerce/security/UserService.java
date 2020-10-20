package com.br.ecommerce.security;

import org.springframework.security.core.context.SecurityContextHolder;

public class UserService {

    public static UsuarioLogado authenticated() {
        return (UsuarioLogado) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
