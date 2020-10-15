package com.br.ecommerce.security;

import com.br.ecommerce.model.Usuario;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppUserDetailsMapper implements UserDetailsMapper{

    @Override
    public UsuarioLogado map(Object shouldBeASystemUser) {
        if( shouldBeASystemUser instanceof Usuario) {
            return new UsuarioLogado((Usuario) shouldBeASystemUser);
        }
        return null;
    }
}
