package com.br.ecommerce.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public interface UserDetailsMapper {

    UserDetails map(Object shouldBeASystemUser);
}
