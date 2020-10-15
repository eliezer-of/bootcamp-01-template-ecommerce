package com.br.ecommerce.controller;

import com.br.ecommerce.model.Usuario;
import com.br.ecommerce.requests.UsuarioRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api")
public class UsuarioController {

    @Autowired
    EntityManager manager;

    @PostMapping(value = "/usuario")
    @Transactional
    public ResponseEntity<?> inserir (@Valid @RequestBody UsuarioRequest request,
                                      UriComponentsBuilder uriComponentsBuilder) {

        Usuario usuario = request.toModel();
        manager.persist(usuario);

        return  ResponseEntity.created(uriComponentsBuilder.path("/api/usuario/{id}").
                buildAndExpand(usuario.getId()).toUri()).build();
    }

}
