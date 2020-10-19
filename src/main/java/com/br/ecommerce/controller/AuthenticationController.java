package com.br.ecommerce.controller;

import com.br.ecommerce.security.JwtUtil;
import com.br.ecommerce.security.LoginInputDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil tokenManger;

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> authenticate (@RequestBody LoginInputDto login) {

        UsernamePasswordAuthenticationToken authenticationToken = login.buil();

        try {
            Authentication authentication = authManager.authenticate(authenticationToken);
            String jtw = tokenManger.generateToken(authentication);

            return ResponseEntity.ok(jtw);
        } catch (ArithmeticException e) {
            log.error("[Autenticação]", e);
            return ResponseEntity.badRequest().build();
        }

    }
}
